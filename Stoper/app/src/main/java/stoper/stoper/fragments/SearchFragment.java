package stoper.stoper.fragments;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

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
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import cz.msebera.android.httpclient.Header;
import stoper.stoper.R;
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
                /*client.get("http://172.16.102.177:8080/search", new JsonHttpResponseHandler() {
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                        Log.i("tagic","stiglo nesto");
                        Log.i("S",response.toString());
                        for(int i=0;i<response.length();i++){
                            Gson gson = new Gson();
                            try {
                                Ride object = gson.fromJson(response.getJSONObject(i).toString(), Ride.class);
                                Log.i("dosao ride",object.toString());
                            }catch(JSONException ex){
                                Log.i("GREeska",ex.getMessage());
                            }

                        }
                    }
                });*/
                Ride r1=new Ride();
                r1.setEndDestination("Paragoooovo");
                r1.setStartDestination("Fojnica");
                r1.setPrice(345);
                Ride r2=new Ride();
                r2.setEndDestination("Pariz");
                r2.setStartDestination("Nica");
                r2.setPrice(444);
                ArrayList<Ride> lista=new ArrayList<>();
                lista.add(r1);
                lista.add(r2);
                Fragment f = new RidesFragment();
                //f.setArguments(b);
                b.putParcelableArrayList("rideList",lista);
                f.setArguments(b);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.main_screen, f);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

    }


}
