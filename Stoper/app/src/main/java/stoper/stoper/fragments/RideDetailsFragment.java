package stoper.stoper.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import stoper.stoper.R;
import stoper.stoper.model.Ride;

/**
 * A simple {@link Fragment} subclass.
 */
public class RideDetailsFragment extends Fragment {

    private Bundle bundle;

    TextView textView;

    public RideDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ride_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bundle = getArguments();
        Ride ride = bundle.getParcelable("selectedRide");
        textView = view.findViewById(R.id.fragment_ride_details_id);
        textView.setText(ride.getStartDestination());
        //Toast.makeText(getContext(), "Voznja " + ride.getStartDestination(),Toast.LENGTH_SHORT);
    }
}
