package stoper.stoper.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;

import stoper.stoper.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class OfferPlacesFragment extends Fragment {
    private NumberPicker numberPicker;


    public OfferPlacesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_offer_places, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        numberPicker = view.findViewById(R.id.number_of_places);
        numberPicker.setMinValue(1);

        numberPicker.setMaxValue(4);
        numberPicker.setValue(3);
    }
}
