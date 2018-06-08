package stoper.stoper.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;

import stoper.stoper.R;
import stoper.stoper.model.Ride;

public class RideDetailsActivity extends AppCompatActivity {

    TextView startDestination;
    TextView endDestionation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_details);

        Intent intent = getIntent();

        Ride ride = new Gson().fromJson(intent.getExtras().getString("selectedRide"), Ride.class);


        startDestination = findViewById(R.id.start_destination_details);
        endDestionation = findViewById(R.id.end_destination_details);
        startDestination.setText(ride.getStartDestination());
        endDestionation.setText(ride.getEndDestination());


    }
}
