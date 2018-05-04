package stoper.stoper.fragments;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TimePicker;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import stoper.stoper.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    private Button searchButton;
    private Bundle b;
    private NumberPicker nmpck;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle(R.string.find_ride);

        b = getArguments();
        EditText et1 = view.findViewById(R.id.start_destination);
        EditText et2 = view.findViewById(R.id.end_destination);
        if(b!=null) {
            String startDestination = b.getString("startDestination");
            if (startDestination != null) {
                et1.setText(startDestination);
            }
            String endDestination = b.getString("endDestination");
            if(endDestination!=null){
                et2.setText(endDestination);
            }
        }else{
            b=new Bundle();
        }

        et1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment f = new DestinationFragment();
                b.putString("type","startDestination");
                f.setArguments(b);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.main_screen, f);
                ft.addToBackStack(null);
                ft.commit();
            }

        });
        et2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Fragment f = new DestinationFragment();
                b.putString("type","endDestination");
                f.setArguments(b);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.main_screen, f);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        /**
         * Date picker
         */
        final Calendar myCalendar = Calendar.getInstance();

        final EditText et3= (EditText) view.findViewById(R.id.date);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
            private void updateLabel() {
                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                et3.setText(sdf.format(myCalendar.getTime()));
            }

        };

        et3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        /**
         * Time picker
         */
        final EditText et4=view.findViewById(R.id.departure_after);
        et4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        et4.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

        /**
         * Number picker
         */
        nmpck=view.findViewById(R.id.numberPicker);
        nmpck.setMaxValue(3);
        nmpck.setMinValue(1);
        nmpck.setWrapSelectorWheel(false);
        nmpck.setOrientation(0);

    }

}
