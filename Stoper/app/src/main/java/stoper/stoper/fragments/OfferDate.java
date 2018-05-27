package stoper.stoper.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Date;

import stoper.stoper.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class OfferDate extends Fragment {

    DatePicker datePicker;
    long minDate = System.currentTimeMillis() - 1000;
    private Button nextButton;
    private FragmentActivity myContext;
    private Fragment fragment;
    Bundle bundle;

    public OfferDate() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_offer2, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bundle = getArguments();
        datePicker = view.findViewById(R.id.start_date_offer);

        datePicker.setMinDate(minDate);

        nextButton = view.findViewById(R.id.button_offer2_id);
        System.out.println("Pocetna adresa: " + bundle.getString("endDestinationOffer"));
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new OfferTimeFragment();
                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth() + 1;
                int year = datePicker.getYear();
                bundle.putInt("dayOffer", day);
                bundle.putInt("monthOffer", month);
                bundle.putInt("yearOffer", year);
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
