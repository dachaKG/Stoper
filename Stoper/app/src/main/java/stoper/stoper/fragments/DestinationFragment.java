package stoper.stoper.fragments;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SyncStatusObserver;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import stoper.stoper.R;
import stoper.stoper.util.HttpDataHandler;
import stoper.stoper.util.MyService;

/**
 * A simple {@link Fragment} subclass.
 */
public class DestinationFragment extends Fragment{


    public DestinationFragment() {
        // Required empty public constructor
    }

    private BroadcastReceiver broadcastReceiver;

    @Override
    public void onResume() {
        super.onResume();
        if(broadcastReceiver == null){
            broadcastReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    //Toast.makeText(getContext(), intent.getStringExtra("coordinates"), Toast.LENGTH_LONG).show();
                    String[] el=intent.getStringExtra("coordinates").split(" ");
                    Geocoder gcd = new Geocoder(context, Locale.getDefault());
                    List<Address> addresses = null;
                    try {
                        addresses = gcd.getFromLocation(Double.parseDouble(el[1]), Double.parseDouble(el[0]), 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (addresses.size() > 0) {
                        //System.out.println(addresses.get(0).getLocality());
                        Intent i = new Intent(getContext(),MyService.class);
                        getActivity().stopService(i);

                        FragmentManager fragmentManager = getFragmentManager();
                        SearchFragment f1= (SearchFragment) fragmentManager.findFragmentByTag("searchFragment");
                        f1.setStartDestination(addresses.get(0).getLocality());
                        //EditText et=f1.getView().findViewById(R.id.start_destination);
                        //System.out.println(et);
                        //et.setText(addresses.get(0).getLocality());
                        fragmentManager.popBackStack();
                        /*FragmentTransaction ft = fragmentManager.beginTransaction();

                        ft.replace(R.id.main_screen, new SearchFragment());
                        ft.addToBackStack(null);
                        ft.commit();*/
                    }
                    else {
                        // do your stuff
                    }


                }
            };
        }
        getContext().registerReceiver(broadcastReceiver,new IntentFilter("location_update"));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(broadcastReceiver != null){
            getContext().unregisterReceiver(broadcastReceiver);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_destination, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button mEditInit = (Button) view.findViewById(R.id.my_location);

        mEditInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new GetCoordinates().execute("Kikinda");
                Intent i =new Intent(getContext().getApplicationContext(),MyService.class);
                getActivity().startService(i);
                //Toast.makeText(v.getContext(), "okolo svuda je tama", Toast.LENGTH_LONG).show();

            }
        });
    }



    private class GetCoordinates extends AsyncTask<String,Void,String> {

        ProgressDialog dialog = new ProgressDialog(getContext());
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Please wait....");
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }
        @Override
        protected String doInBackground(String... strings) {
            String response;
            try {
                String address = strings[0];
                HttpDataHandler http = new HttpDataHandler();
                String url = String.format("https://maps.googleapis.com/maps/api/geocode/json?address=%s", address);
                response = http.getHTTPData(url);
                return response;
            } catch (Exception ex) {
            }
            return null;
        }


        @Override
        protected void onPostExecute(String s) {
            try{
                JSONObject jsonObject = new JSONObject(s);
                String lat = ((JSONArray)jsonObject.get("results")).getJSONObject(0).getJSONObject("geometry")
                        .getJSONObject("location").get("lat").toString();
                String lng = ((JSONArray)jsonObject.get("results")).getJSONObject(0).getJSONObject("geometry")
                        .getJSONObject("location").get("lng").toString();
                if(dialog.isShowing())

                    dialog.dismiss();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
