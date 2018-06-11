package stoper.stoper.fragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import stoper.stoper.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class OfferFragment extends Fragment {

    Button nextButton;
    Fragment fragment;
    EditText startDestination;
    EditText endDestination;

    Bundle bundle;
    private FragmentActivity myContext;

    public OfferFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_offer, container, false);


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_offer, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //bundle = new Bundle();
        startDestination =  view.findViewById(R.id.start_destination_offer);
        endDestination = view.findViewById(R.id.end_destination_offer);
        nextButton = view.findViewById(R.id.button_offer_id);

        bundle = getArguments();
        if(bundle!=null) {
            String startDestination = bundle.getString("startDestination");
            if (startDestination != null) {
                this.startDestination.setText(startDestination);
            }
            String endDestination = bundle.getString("endDestination");
            if(endDestination!=null){
                this.endDestination.setText(endDestination);
            }
        }else{
            bundle=new Bundle();
        }
        bundle.putString("fragment","offer");
        startDestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment f = new DestinationFragment();
                bundle.putString("type","startDestination");
                f.setArguments(bundle);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.main_screen, f);
                ft.addToBackStack(null);
                ft.commit();
            }

        });
        endDestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment f = new DestinationFragment();
                bundle.putString("type","endDestination");
                f.setArguments(bundle);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.main_screen, f);
                ft.addToBackStack(null);
                ft.commit();
            }

        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new OfferDate();
                bundle.putString("startDestinationOffer", startDestination.getText().toString().trim());
                bundle.putString("endDestinationOffer", endDestination.getText().toString().trim());
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.main_screen, fragment);
                ft.addToBackStack(null);
                ft.commit();
                //new HttpReqTask().execute();
            }
        });

    }




    private class HttpReqTask extends AsyncTask<Void, Void, String>{

        @Override
        protected String doInBackground(Void... voids) {
            try{
                String apiUrl = "http://172.16.96.187:8080/proba";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                String proba = restTemplate.getForObject(apiUrl, String.class);

                return proba;
            } catch (Exception ex) {
                Log.e("", ex.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


            //Log.i("Proba: ", String.valueOf(s));
        }
    }

}
