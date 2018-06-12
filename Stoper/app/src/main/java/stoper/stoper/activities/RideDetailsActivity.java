package stoper.stoper.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import stoper.stoper.Api;
import stoper.stoper.DTO.RideReservationDTO;
import stoper.stoper.DTO.UserImageDTO;
import stoper.stoper.DTO.UserPhoneNumberDTO;
import stoper.stoper.R;
import stoper.stoper.chat.core.users.getAll.GetUsersContract;
import stoper.stoper.chat.core.users.getAll.GetUsersInteractor;
import stoper.stoper.chat.model.ChatUser;
import stoper.stoper.chat.ui.activity.ChatActivity;
import stoper.stoper.chat.utils.Constants;
import stoper.stoper.model.Ride;
import stoper.stoper.model.User;

import static java.lang.System.out;

public class RideDetailsActivity extends AppCompatActivity {

    TextView startDestination;
    TextView endDestionation;
    Ride ride = null;
    String driverEmail;
    String loggedEmail = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_details);

        Intent intent = getIntent();

        ride = new Gson().fromJson(intent.getExtras().getString("selectedRide"), Ride.class);

        TextView startDestination = (TextView) findViewById(R.id.ride_end_destination);
        startDestination.setText(ride.getStartDestination());

        TextView endDestination = (TextView) findViewById(R.id.ride_end_destination);
        endDestination.setText(ride.getEndDestination());

        TextView price = (TextView) findViewById(R.id.ride_price);
        price.setText(String.format("%s %s", ride.getPrice().toString(), getResources().getString(R.string.currency)));

        String dateString = ride.getRideDate();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(dateString);
            if (date != null) {
                SimpleDateFormat formaTime = new SimpleDateFormat("HH:mm");
                String shortTimeStr = formaTime.format(date);
                TextView startTime = (TextView) findViewById(R.id.ride_starting_time);
                startTime.setText(String.format("%s - ", shortTimeStr));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        TextView endTime = (TextView) findViewById(R.id.ride_estimated_time);
        endTime.setText(String.format(" - "));

        TextView startAddress = (TextView) findViewById(R.id.ride_starting_address);
        startAddress.setText("Adresa po dogovoru");

        TextView endAddress = (TextView) findViewById(R.id.ride_target_address);
        endAddress.setText("Adresa po dogovoru");

        TextView freeSpace = (TextView) findViewById(R.id.ride_free_space);
        freeSpace.setText(ride.getPassengerNumber().toString());

        TextView maxPassengers = (TextView) findViewById(R.id.ride_max_space);
        maxPassengers.setText(ride.getMaxPassengerNum().toString());

        TaskGetUserByEmail task = new TaskGetUserByEmail();
        task.execute(ride.getDriver().getEmail());

        SharedPreferences loggedUserDetails = getApplicationContext().getSharedPreferences(Api.baseName, MODE_PRIVATE);

        loggedEmail = loggedUserDetails.getString("email", "");
    }

    public void onClickReserve(View view) {
        Long rideId = ride.getId();

        RideReservationDTO rideReservationDTO = new RideReservationDTO();
        rideReservationDTO.setPassengerEmail(loggedEmail);
        rideReservationDTO.setRideId(ride.getId());
        TaskReserve task = new TaskReserve();
        task.execute(rideReservationDTO);

    }

    public void onClickContactDriver(View view) {
        SharedPreferences loggedUserDetails;

        loggedUserDetails = getApplicationContext().getSharedPreferences(Api.baseName, MODE_PRIVATE);

        getUserFromFirebase(loggedUserDetails.getString("drive_email", ""), this);

    }


    public void getUserFromFirebase(final String email, final Context context) {
        //ChatUser chatUser = new ChatUser();
        FirebaseDatabase.getInstance().getReference().child(Constants.ARG_USERS).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> dataSnapshots = dataSnapshot.getChildren().iterator();

                ChatUser chatUser = new ChatUser();
                while (dataSnapshots.hasNext()) {
                    DataSnapshot dataSnapshotChild = dataSnapshots.next();

                    ChatUser user = dataSnapshotChild.getValue(ChatUser.class);

                    if (user.email.toString().equals(email)) {
                        chatUser = user;
                        break;
                    }
                }
                ChatActivity.startActivity(context,
                        chatUser.email,
                        chatUser.uid,
                        chatUser.firebaseToken);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private class TaskGetUserByEmail extends AsyncTask<String, Void, User> {

        @Override
        protected User doInBackground(String... email) {
            try {
                String apiUrl = Api.apiUrl + "/user/email/" + email[0];
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                User proba = restTemplate.getForObject(apiUrl, User.class);

                return proba;
            } catch (Exception ex) {
                Log.e("", ex.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(User user) {
            super.onPostExecute(user);
            driverEmail = user.getEmail();

            SharedPreferences loggedUserDetails;

            loggedUserDetails = getApplicationContext().getSharedPreferences(Api.baseName, MODE_PRIVATE);

            SharedPreferences.Editor edit = loggedUserDetails.edit();
            edit.putString("drive_email", user.getEmail());

            edit.apply();

            if (user.getCarBrand() != null && !user.getCarBrandModel().equals("")) {
                TextView carBrand = (TextView) findViewById(R.id.ride_car_brand);
                carBrand.setText(String.format("%s %s", user.getCarBrand(), user.getCarBrandModel()));
            }
            if (user.getCarColor() != null) {
                TextView carColor = (TextView) findViewById(R.id.ride_car_color);
                carColor.setText(user.getCarColor());
            }

            if (user.getFirstName() != null) {
                TextView firstName = (TextView) findViewById(R.id.ride_driver_first_name);
                firstName.setText(user.getFirstName());
            }

            if (user.getLevel() != null) {
                TextView level = (TextView) findViewById(R.id.ride_driver_level);
                level.setText(user.getLevel());
            }

            List<String> speaking = Arrays.asList(getResources().getStringArray(R.array.speaking_array));
            List<String> smoking = Arrays.asList(getResources().getStringArray(R.array.smoking_array));
            List<String> music = Arrays.asList(getResources().getStringArray(R.array.music_array));
            List<String> pets = Arrays.asList(getResources().getStringArray(R.array.pets_array));

            if (user.getSpeaking() != null) {
                TextView speakingContent = (TextView) findViewById(R.id.show_profile_custom_settings_speking_content);
                speakingContent.setText(speaking.get(user.getSpeaking()));
            }

            if (user.getSmoking() != null) {
                TextView smokingContent = (TextView) findViewById(R.id.show_profile_custom_settings_smoking_content);
                smokingContent.setText(smoking.get(user.getSmoking()));
            }

            if (user.getMusic() != null) {
                TextView musicContent = (TextView) findViewById(R.id.show_profile_custom_settings_music_content);
                musicContent.setText(music.get(user.getMusic()));
            }

            if (user.getPets() != null) {
                TextView petsContent = (TextView) findViewById(R.id.show_profile_custom_settings_pets_content);
                petsContent.setText(pets.get(user.getPets()));
            }


            ImageView driver_image = (ImageView) findViewById(R.id.ride_driver_submitted_image);
            TextView emailStatusText = (TextView) findViewById(R.id.ride_driver_email_address_status);
            if (user.getConfirmed() != null && user.getConfirmed()) {
                driver_image.setImageResource(R.drawable.baseline_check_circle_outline_24);
                emailStatusText.setText(R.string.show_profile_email_status_confirmed);
            } else {
                driver_image.setImageResource(R.drawable.baseline_highlight_off_24);
                emailStatusText.setText(R.string.show_profile_email_status_not_confirmed);
            }
            ImageView imageView = (ImageView) findViewById(R.id.ride_driver_profile);
            if (user != null && user.getProfileImage() != null && user.getProfileImage().length > 0) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(user.getProfileImage(), 0, user.getProfileImage().length);
                imageView.setImageBitmap(bitmap);
            }
        }
    }

    private class TaskReserve extends AsyncTask<RideReservationDTO, Void, Boolean> {

        @Override
        protected Boolean doInBackground(RideReservationDTO... rideReservationDTOS) {
            try {
                String apiUrl = Api.apiUrl + "/ride/reserve";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                HttpEntity<RideReservationDTO> data = new HttpEntity<>(rideReservationDTOS[0]);
                ResponseEntity<Boolean> proba = restTemplate.exchange(apiUrl, HttpMethod.POST, data, Boolean.class);

                return proba.getBody();
            } catch (Exception ex) {
                Log.e("", ex.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            if (result) {
                Intent intent = new Intent(RideDetailsActivity.this, SuccessReserveActivity.class);
                startActivity(intent);
            } else {
                showMessageSuccess();
            }
        }
    }

    public void onClickShowDriverProfile(View view){
        TextView nameView = (TextView)view;
        Intent intent = new Intent(RideDetailsActivity.this, ShowProfileActivity.class);
        intent.putExtra("userReference", driverEmail);
        startActivity(intent);
    }

    private void showMessageSuccess() {
        Context contex = getApplicationContext();
        CharSequence text = "@string/message_error_reserve";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(contex, text, duration);
        toast.show();
    }
}
