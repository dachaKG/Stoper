package stoper.stoper.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.github.bassaer.chatmessageview.model.Message;
import com.github.bassaer.chatmessageview.view.ChatView;
import com.github.bassaer.chatmessageview.view.MessageView;
import com.google.gson.Gson;

import stoper.stoper.R;
import stoper.stoper.model.Ride;
import stoper.stoper.model.User;
import stoper.stoper.util.MockData;

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
