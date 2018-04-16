package stoper.stoper.fragments;


import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
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

import java.util.Locale;

import stoper.stoper.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DestinationFragment extends Fragment implements OnMapReadyCallback{

    private GoogleMap mMap;

    public DestinationFragment() {
        // Required empty public constructor
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

        if(mEditInit==null)
            Toast.makeText(view.getContext(), "null je jebo ti on mater", Toast.LENGTH_LONG).show();

        mEditInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "okolo svuda je tama", Toast.LENGTH_LONG).show();
                Location l=mMap.getMyLocation();
                //Geocoder gcd = new Geocoder(context, Locale.getDefault());
                //Toast.makeText(v.getContext(),l.get)
            }
        });
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap=googleMap;
        /*if(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION},177);
            }
        }else{
            mMap.setMyLocationEnabled(true);
        }*/

    }
}
