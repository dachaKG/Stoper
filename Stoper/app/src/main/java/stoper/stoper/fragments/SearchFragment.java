package stoper.stoper.fragments;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TimePicker;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import cz.msebera.android.httpclient.Header;
import stoper.stoper.Api;
import stoper.stoper.R;
import stoper.stoper.activities.RideDetailsActivity;
import stoper.stoper.adapter.RideFragmentAdapter;
import stoper.stoper.model.Ride;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    private Button searchButton;
    private Bundle b;
    private NumberPicker nmpck;
    private static AsyncHttpClient client = new AsyncHttpClient();


    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle(R.string.find_ride);

        b = getArguments();
        EditText et1 = view.findViewById(R.id.start_destination);
        EditText et2 = view.findViewById(R.id.end_destination);
        if(b!=null) {
            String startDestination = b.getString("startDestination");
            if (startDestination != null) {
                et1.setText(startDestination);
            }
            String endDestination = b.getString("endDestination");
            if(endDestination!=null){
                et2.setText(endDestination);
            }
        }else{
            b=new Bundle();
        }

        et1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment f = new DestinationFragment();
                b.putString("type","startDestination");
                f.setArguments(b);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.main_screen, f);
                ft.addToBackStack(null);
                ft.commit();
            }

        });
        et2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Fragment f = new DestinationFragment();
                b.putString("type","endDestination");
                f.setArguments(b);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.main_screen, f);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        /**
         * Date picker
         */
        final Calendar myCalendar = Calendar.getInstance();

        final EditText et3= (EditText) view.findViewById(R.id.date);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
            private void updateLabel() {
                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                et3.setText(sdf.format(myCalendar.getTime()));
            }

        };

        et3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });




        /**
         * Number picker
         */
        nmpck=view.findViewById(R.id.numberPicker);
        nmpck.setMaxValue(3);
        nmpck.setMinValue(1);
        nmpck.setWrapSelectorWheel(false);
        nmpck.setOrientation(LinearLayout.HORIZONTAL);


        /**
         * Search button listener
         */
        searchButton=view.findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ride r=new Ride();
                /*r.setStartDestination(cir2lat(b.getString("startDestination")));
                r.setEndDestination(cir2lat(b.getString("endDestination")));
                String s=et3.getText().toString();
                String[] niz=s.split("/");
                String zaUbacit="20"+niz[2]+"-"+niz[0]+"-"+niz[1]+" 00:00:00";
                r.setRideDate(zaUbacit);
                r.setPassengerNumber(nmpck.getValue());*/

                r.setRideDate("2018-06-07 00:00:00");
                r.setStartDestination("Novi Sad");
                r.setEndDestination("Kragujevac");
                r.setPassengerNumber(1);
                new HttpReqTask().execute(r);



            }
        });

    }

    private class HttpReqTask extends AsyncTask<Ride, Void, Ride[]> {


        @Override
        protected Ride[] doInBackground(Ride... rides) {
            try {
                String apiUrl = Api.apiUrl + "/ride/searchRides";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                HttpEntity<Ride> ride = new HttpEntity<>(rides[0]);
                Ride[] getRides = (Ride[]) restTemplate.postForObject(apiUrl,ride, Ride[].class);
                return getRides;
            } catch (Exception ex) {
                Log.e("", ex.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(Ride[] rides) {
            super.onPostExecute(rides);
            Bundle bundle=new Bundle();
            bundle.putParcelableArrayList("ridesList", new ArrayList<>(Arrays.asList(rides)));

            Fragment f = new RidesFragment();
            //f.setArguments(b);
            f.setArguments(bundle);
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.main_screen, f);
            ft.addToBackStack(null);
            ft.commit();
        }


    }

    public static String cir2lat(String text) {
        String ret = "";
        for (int i = 0; i < text.length(); i++) {
            char c=text.charAt(i);
            switch(c){
                case '\u0430': ret+="a"; break;
                case '\u0431': ret+="b"; break;
                case '\u0446': ret+="c"; break;
                case '\u0434': ret+="d"; break;
                case '\u0435': ret+="e"; break;
                case '\u0444': ret+="f"; break;
                case '\u0433': ret+="g"; break;
                case '\u0445': ret+="h"; break;
                case '\u0438': ret+="i"; break;
                case '\u0458': ret+="j"; break;
                case '\u043A': ret+="k"; break;
                case '\u043B': ret+="l"; break;
                case '\u043C': ret+="m"; break;
                case '\u043D': ret+="n"; break;
                case '\u043E': ret+="o"; break;
                case '\u043F': ret+="p"; break;
                case '\u0440': ret+="r"; break;
                case '\u0441': ret+="s"; break;
                case '\u0442': ret+="t"; break;
                case '\u0443': ret+="u"; break;
                case '\u0432': ret+="v"; break;
                case '\u0437': ret+="z"; break;
                case '\u0410': ret+="A"; break;
                case '\u0411': ret+="B"; break;
                case '\u0426': ret+="C"; break;
                case '\u0414': ret+="D"; break;
                case '\u0415': ret+="E"; break;
                case '\u0424': ret+="F"; break;
                case '\u0413': ret+="G"; break;
                case '\u0425': ret+="H"; break;
                case '\u0418': ret+="I"; break;
                case '\u0408': ret+="J"; break;
                case '\u041A': ret+="K"; break;
                case '\u041B': ret+="L"; break;
                case '\u041C': ret+="M"; break;
                case '\u041D': ret+="N"; break;
                case '\u041E': ret+="O"; break;
                case '\u041F': ret+="P"; break;
                case '\u0420': ret+="R"; break;
                case '\u0421': ret+="S"; break;
                case '\u0422': ret+="T"; break;
                case '\u0423': ret+="U"; break;
                case '\u0412': ret+="V"; break;
                case '\u0417': ret+="Z"; break;
                case '\u045B': ret+="\u0107"; break;
                case '\u0447': ret+="\u010D"; break;
                case '\u0452': ret+="\u0111"; break;
                case '\u0448': ret+="\u0161"; break;
                case '\u0436': ret+="\u017E"; break;
                case '\u040B': ret+="\u0106"; break;
                case '\u0427': ret+="\u010C"; break;
                case '\u0402': ret+="\u0110"; break;
                case '\u0428': ret+="\u0160"; break;
                case '\u0416': ret+="\u017D"; break;
                case '\u045F': ret+="d\u017E";break;
                case '\u0459': ret+="lj";break;
                case '\u045A': ret+="nj";break;
                case '\u040F': ret+="D\u017E";break;
                case '\u0409': ret+="Lj";break;
                case '\u040A': ret+="Nj";break;
                default : ret+=c;
            }
        }
        return ret;
    }

}
