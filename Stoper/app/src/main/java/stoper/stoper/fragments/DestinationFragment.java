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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import stoper.stoper.R;
import stoper.stoper.util.HttpDataHandler;
import stoper.stoper.util.MyService;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class DestinationFragment extends Fragment{


    public DestinationFragment() {
        // Required empty public constructor
    }

    private BroadcastReceiver broadcastReceiver;

    ProgressDialog dialog;
    private static View view;

    private Bundle b;

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
                        //SearchFragment f1= (SearchFragment) fragmentManager.findFragmentByTag("searchFragment");
                        //f1.setStartDestination(addresses.get(0).getLocality());
                        if(b.getString("type").equals("startDestination"))
                            b.putString("startDestination",addresses.get(0).getLocality());
                        else if(b.getString("type").equals("endDestination"))
                            b.putString("endDestination",addresses.get(0).getLocality());
                        SearchFragment f1=new SearchFragment();
                        f1.setArguments(b);
                        //fragmentManager.popBackStack();

                        if(dialog.isShowing())

                            dialog.dismiss();

                        FragmentTransaction ft = fragmentManager.beginTransaction();
                        ft.replace(R.id.main_screen, f1);
                        //ft.addToBackStack(null);
                        ft.commit();
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
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button mEditInit = (Button) view.findViewById(R.id.my_location);
        dialog = new ProgressDialog(getContext());
        b=getArguments();

        mEditInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getContext().getApplicationContext(),MyService.class);
                getActivity().startService(i);

                dialog.setMessage("Please wait....");
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
                //Toast.makeText(v.getContext(), "okolo svuda je tama", Toast.LENGTH_LONG).show();

            }
        });

        EditText editText = (EditText) view.findViewById(R.id.start_typing);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment f = new PlacesFragment();
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
