package stoper.stoper.fragments;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.google.gson.Gson;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;

import stoper.stoper.Api;
import stoper.stoper.R;
import stoper.stoper.activities.NavigationActivity;
import stoper.stoper.model.Ride;

import static java.lang.System.out;

/**
 * A simple {@link Fragment} subclass.
 */
public class OfferNoteFragment extends Fragment {

    Bundle bundle;
    EditText editTextNote;
    Button finishButton;


    public OfferNoteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_offer_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bundle = getArguments();
        editTextNote = view.findViewById(R.id.offer_note_id);

        bundle.putString("noteOffer", editTextNote.getText().toString());


        finishButton = view.findViewById(R.id.button_offer_note_id);
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Ride ride = new Ride();
                    ride.setStartDestination(bundle.getString("startDestinationOffer"));
                    ride.setEndDestination(bundle.getString("endDestinationOffer"));
                    ride.setPassengerNumber(bundle.getInt("numberOfPassengers"));
                    ride.setPrice(bundle.getInt("priceOffer"));
                    ride.setNote(editTextNote.getText().toString());
                    String rideDate = String.valueOf(bundle.getInt("yearOffer")) + "-" + String.valueOf(bundle.getInt("monthOffer")) + "-" + String.valueOf(bundle.getInt("dayOffer")) + "T" +
                            String.valueOf(bundle.getInt("hourOffer")) + ":" + String.valueOf(bundle.getInt("minuteOffer")) + ":00";

                    out.println("Vremeeeee: " + rideDate);
                    //String dtStart = "2010-10-15T09:27:37Z";
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                    SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date d = sdf.parse(rideDate);
                    String formattedTime = output.format(d);


                    //Date date = format.parse(rideDate);
                    ride.setRideDate(formattedTime);

                    Gson gson = new Gson();
                    String json = gson.toJson(ride);
                    System.out.println(json);
                    new HttpReqTask().execute(ride);

                    Intent myIntent = new Intent(getActivity(),NavigationActivity.class);
                    startActivity(myIntent);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });




    }


    private class HttpReqTask extends AsyncTask<Ride, Void, Ride> {


        @Override
        protected Ride doInBackground(Ride... rides) {
            try {
                String apiUrl = Api.apiUrl + "/proba";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                HttpEntity<Ride> ride = new HttpEntity<>(rides[0]);
                ResponseEntity<Ride> proba = restTemplate.exchange(apiUrl, HttpMethod.POST,  ride, Ride.class);

                return proba.getBody();
            } catch (Exception ex) {
                Log.e("", ex.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(Ride aBoolean) {
            super.onPostExecute(aBoolean);

            out.println("Boolean jeeeeeee " + aBoolean.getId());
        }
    }
}
