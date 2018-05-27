package stoper.stoper.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.NumberPicker;

import stoper.stoper.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class OfferPlacesFragment extends Fragment {
    private NumberPicker numberPicker;
    private Button nextButton;
    private Fragment fragment;
    Bundle bundle;

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
        bundle = getArguments();
        numberPicker = view.findViewById(R.id.number_of_passengers);
        numberPicker.setMinValue(1);

        numberPicker.setMaxValue(4);
        numberPicker.setValue(3);

        nextButton = view.findViewById(R.id.button_offer_passengers_id);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new OfferPriceFragment();
                bundle.putInt("numberOfPassengers", numberPicker.getValue());
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.main_screen, fragment);

                ft.addToBackStack(null);
                ft.commit();
            }
        });

    }
}
