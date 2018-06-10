package stoper.stoper.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import stoper.stoper.R;
import stoper.stoper.fragments.ProfileFragment;
import stoper.stoper.model.User;
import stoper.stoper.util.MockData;

public class ShowProfileActivity extends AppCompatActivity {

    MockData mockData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_profile);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.show_profile_app_bar_title);

        User user = mockData.UsersDatabase().get(0);
        List<String> speaking = Arrays.asList(getResources().getStringArray(R.array.speaking_array));
        List<String> smoking = Arrays.asList(getResources().getStringArray(R.array.smoking_array));
        List<String> music = Arrays.asList(getResources().getStringArray(R.array.music_array));
        List<String> pets = Arrays.asList(getResources().getStringArray(R.array.pets_array));

        TextView speakingContent = (TextView) findViewById(R.id.show_profile_custom_settings_speking_content);
        speakingContent.setText(speaking.get(user.getSpeaking()));

        TextView smokingContent = (TextView) findViewById(R.id.show_profile_custom_settings_smoking_content);
        smokingContent.setText(smoking.get(user.getSmoking()));

        TextView musicContent = (TextView) findViewById(R.id.show_profile_custom_settings_music_content);
        musicContent.setText(music.get(user.getMusic()));

        TextView petsContent = (TextView) findViewById(R.id.show_profile_custom_settings_pets_content);
        petsContent.setText(pets.get(user.getPets()));

        ImageView imageView = (ImageView) findViewById(R.id.show_profil_submitted_image);
        TextView textView = (TextView) findViewById(R.id.show_profile_email_address_status);
        if (user.getConfirmed()) {
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

        ImageView profile_image = (ImageView) findViewById(R.id.profile_image);
        if (user.getProfileImage() != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(user.getProfileImage(), 0, user.getProfileImage().length);

            profile_image.setImageBitmap(bitmap);
        }
    }
}
