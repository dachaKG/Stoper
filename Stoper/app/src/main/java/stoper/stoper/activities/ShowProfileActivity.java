package stoper.stoper.activities;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

import stoper.stoper.Api;
import stoper.stoper.R;
import stoper.stoper.fragments.ProfileFragment;
import stoper.stoper.model.User;
import stoper.stoper.util.MockData;

public class ShowProfileActivity extends AppCompatActivity {

    //MockData mockData;
    User user;
    SharedPreferences loggedUserDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_profile);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        loggedUserDetails = getApplicationContext().getSharedPreferences(Api.baseName, MODE_PRIVATE);
        user = new Gson().fromJson(loggedUserDetails.getString("userJson", ""), User.class);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.show_profile_app_bar_title);

        TextView toolabarName = (TextView)findViewById(R.id.profile_head_name);
        toolabarName.setText(user.getFirstName());

        if(user.getLevel() != null){
            TextView level = (TextView)findViewById(R.id.profile_head_level);
            level.setText(user.getLevel());
        }

        if(user.getProfileImage() != null && user.getProfileImage().length > 0) {
            ImageView imageView = (ImageView) findViewById(R.id.profile_image);
            Bitmap bitmap = BitmapFactory.decodeByteArray(user.getProfileImage(), 0, user.getProfileImage().length);
            imageView.setImageBitmap(bitmap);
        }
        List<String> speaking = Arrays.asList(getResources().getStringArray(R.array.speaking_array));
        List<String> smoking = Arrays.asList(getResources().getStringArray(R.array.smoking_array));
        List<String> music = Arrays.asList(getResources().getStringArray(R.array.music_array));
        List<String> pets = Arrays.asList(getResources().getStringArray(R.array.pets_array));
        List<String> colors = Arrays.asList(getResources().getStringArray(R.array.colors));

        if (user.getSpeaking() != null) {
            TextView speakingContent = (TextView) findViewById(R.id.show_profile_custom_settings_speking_content);
            speakingContent.setText(speaking.get(user.getSpeaking()));
        }

        if(user.getSmoking() != null) {
            TextView smokingContent = (TextView) findViewById(R.id.show_profile_custom_settings_smoking_content);
            smokingContent.setText(smoking.get(user.getSmoking()));
        }

        if(user.getMusic() != null) {
            TextView musicContent = (TextView) findViewById(R.id.show_profile_custom_settings_music_content);
            musicContent.setText(music.get(user.getMusic()));
        }
        if(user.getPets() != null) {
            TextView petsContent = (TextView) findViewById(R.id.show_profile_custom_settings_pets_content);
            petsContent.setText(pets.get(user.getPets()));
        }
        ImageView imageView = (ImageView) findViewById(R.id.show_profil_submitted_image);
        TextView textView = (TextView) findViewById(R.id.show_profile_email_address_status);
        if (user.getConfirmed() != null && user.getConfirmed()) {
            imageView.setImageResource(R.drawable.baseline_check_circle_outline_24);
            textView.setText(R.string.show_profile_email_status_confirmed);
        } else {
            imageView.setImageResource(R.drawable.baseline_highlight_off_24);
            textView.setText(R.string.show_profile_email_status_not_confirmed);
        }

        TextView car_model = (TextView) findViewById(R.id.show_profile_car_model);
        if (user.getCarBrand() != null && user.getCarBrandModel() != null) {
            car_model.setText(String.format("%s %s", user.getCarBrand(), user.getCarBrandModel()));
            car_model.setVisibility(View.VISIBLE);
        }

        if (user.getCarColor() != null){
            TextView carColor = (TextView)findViewById(R.id.show_profile_car_color);
            carColor.setText(colors.get(user.getCarColor()));
            carColor.setVisibility(View.VISIBLE);
        }

        if (user.getCarYear() != null && user.getCarYear() != 0){
            TextView carYear = (TextView)findViewById(R.id.show_profile_car_year);
            carYear.setText(user.getCarYear().toString());
            carYear.setVisibility(View.VISIBLE);
        }


        ImageView profile_image = (ImageView) findViewById(R.id.profile_image);
        if (user.getProfileImage() != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(user.getProfileImage(), 0, user.getProfileImage().length);

            profile_image.setImageBitmap(bitmap);
        }

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()) {
            case
                    android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
