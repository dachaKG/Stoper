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
import android.widget.NumberPicker;
import android.widget.TimePicker;

import stoper.stoper.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class OfferTimeFragment extends Fragment {

    private Button nextButton;
    private Fragment fragment;
    private Bundle bundle;
    private TimePicker timePicker;

    public OfferTimeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_offer_time, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bundle = getArguments();
        timePicker = view.findViewById(R.id.time_picker_id);
        nextButton = view.findViewById(R.id.button_offer_time_id);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new OfferPlacesFragment();
                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();
                bundle.putInt("hourOffer", hour);
                bundle.putInt("minuteOffer", minute);
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
