package stoper.stoper.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.text.Line;
import com.google.gson.Gson;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import stoper.stoper.Api;
import stoper.stoper.DTO.UserCustomSettingsDTO;
import stoper.stoper.DTO.UserImageDTO;
import stoper.stoper.DTO.UserPersonalDataDTO;
import stoper.stoper.DTO.UserPhoneNumberDTO;
import stoper.stoper.MainActivity;
import stoper.stoper.R;
import stoper.stoper.fragments.OfferNoteFragment;
import stoper.stoper.model.Ride;
import stoper.stoper.model.User;

import static java.lang.System.out;

public class EditProfileActivity extends AppCompatActivity {

    public static final String EXTRA_SCROLL_TO_ELEMENT = Integer.toString(R.id.first_name_text_view);
    private static int RESULT_LOAD_IMAGE = 1;

    private EditText scrollTo;
    private NestedScrollView scrollView;

    String gender = "";
    String firstName = "";
    String lastName = "";
    String birthYear = "";
    String biography = "";
    String areaNumber = "";
    String phoneNumber = "";
    private User loggedUser;
    private SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.edit_profile_app_bar_title);

        preferences = getApplicationContext().getSharedPreferences(Api.baseName, MODE_PRIVATE);
        loggedUser =  new Gson().fromJson(preferences.getString("userJson", ""), User.class);

        TextView genderView = (TextView) findViewById(R.id.gender_text_view);
        final List<String> gender_array = Arrays.asList(getResources().getStringArray(R.array.gender_array));
        genderView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditProfileActivity.this);
                builder
                        .setItems(R.array.gender_array, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                ((TextView) findViewById(R.id.gender_text_view)).setText(gender_array.get(which));
                            }
                        });
                builder.show();
            }
        });
        final TextView year_text = (TextView) findViewById(R.id.birth_year_text_view);
        ArrayList<String> years = new ArrayList<>();
        int maxYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = maxYear - 18; i > maxYear - 90; i--) {
            years.add(Integer.toString(i));
        }
        final CharSequence[] charSequenceItems = years.toArray(new CharSequence[years.size()]);
        year_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditProfileActivity.this);
                builder.setItems(charSequenceItems, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        year_text.setText(charSequenceItems[i]);
                    }
                });
                builder.show();
            }
        });

        if (savedInstanceState != null) {
            ((TextView) findViewById(R.id.gender_text_view)).setText(savedInstanceState.getString("gender"));
            ((TextView) findViewById(R.id.first_name_text_view)).setText(savedInstanceState.getString("firstName"));
            ((TextView) findViewById(R.id.last_name_text_view)).setText(savedInstanceState.getString("lastName"));
            ((TextView) findViewById(R.id.birth_year_text_view)).setText(savedInstanceState.getString("birthYear"));
            ((TextView) findViewById(R.id.biography_text_view)).setText(savedInstanceState.getString("biography"));
            ((TextView) findViewById(R.id.area_call_text_view)).setText(savedInstanceState.getString("area_number"));
            ((TextView) findViewById(R.id.phone_number_text_view)).setText(savedInstanceState.getString("phone_number"));
        } else {
            int focusElement = (int) getIntent().getExtras().get(EXTRA_SCROLL_TO_ELEMENT);
            EditText b = (EditText) findViewById(focusElement);
            b.requestFocus();
            if (loggedUser.getGender() == 0) {
                ((TextView) findViewById(R.id.gender_text_view)).setText("Muski");

            } else {
                ((TextView) findViewById(R.id.gender_text_view)).setText("Zenski");
            }
            ((TextView) findViewById(R.id.first_name_text_view)).setText(loggedUser.getFirstName());
            ((TextView) findViewById(R.id.last_name_text_view)).setText(loggedUser.getLastName());
            ((TextView) findViewById(R.id.birth_year_text_view)).setText(Integer.toString(loggedUser.getYearOfBirth()));
            ((TextView) findViewById(R.id.biography_text_view)).setText(loggedUser.getBiography());
            ((TextView) findViewById(R.id.area_call_text_view)).setText(loggedUser.getAreaCall());
            ((TextView) findViewById(R.id.phone_number_text_view)).setText(loggedUser.getPhoneNumber());
        }

        GetUserDataTask taks = new GetUserDataTask();
        taks.execute();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String gender_text = ((TextView)findViewById(R.id.gender_text_view)).getText().toString();
        String first_name_text = ((TextView)findViewById(R.id.first_name_text_view)).getText().toString();
        String last_name_text = ((TextView)findViewById(R.id.last_name_text_view)).getText().toString();
        String birth_year_text = ((TextView)findViewById(R.id.birth_year_text_view)).getText().toString();
        String biography_text = ((TextView)findViewById(R.id.biography_text_view)).getText().toString();
        String area_number_text = ((TextView)findViewById(R.id.area_call_text_view)).getText().toString();
        String phone_number_text = ((TextView)findViewById(R.id.phone_number_text_view)).getText().toString();
        outState.putString("gender", gender_text);
        outState.putString("firstName",first_name_text);
        outState.putString("lastName",last_name_text);
        outState.putString("birthYear", birth_year_text);
        outState.putString("biography",biography_text);
        outState.putString("area_number", area_number_text);
        outState.putString("phone_number", phone_number_text);
    }

    public void onClickSaveDataForProfil(View view){
        String edited_gender = ((TextView)findViewById(R.id.gender_text_view)).getText().toString();
        int gender = 0;
        List<String> gender_array = Arrays.asList(getResources().getStringArray(R.array.gender_array));
        gender = gender_array.indexOf(edited_gender);
        String edited_first_name = ((TextView)findViewById(R.id.first_name_text_view)).getText().toString();
        String edited_last_name = ((TextView)findViewById(R.id.last_name_text_view)).getText().toString();
        String edited_birth_year = ((TextView)findViewById(R.id.birth_year_text_view)).getText().toString();
        int year= Integer.parseInt(edited_birth_year);
        String edited_biography = ((TextView)findViewById(R.id.biography_text_view)).getText().toString();

        UserPersonalDataDTO userPersonalDataDTO = new UserPersonalDataDTO(loggedUser.getEmail(),gender,edited_first_name,edited_last_name,year,edited_biography);
        SavePersonalDataTask savePersonalDataTask = new SavePersonalDataTask();
        savePersonalDataTask.execute(userPersonalDataDTO);

        loggedUser.setGender(gender);
        loggedUser.setFirstName(edited_first_name);
        loggedUser.setYearOfBirth(year);
        loggedUser.setLastName(edited_last_name);
        loggedUser.setBiography(edited_biography);
        updateUser();
        showMessageSuccess();
    }

    /*public void onClickSaveEmail(View view){
        String edited_email = ((TextView)findViewById(R.id.email_text_view)).getText().toString();
        UserEmailDTO userEmailDTO = new UserEmailDTO(loggedUser.getEmail(),edited_email, false);
        loggedUser.setEmail(edited_email);

        SaveEmailDataTask saveEmailDataTask = new SaveEmailDataTask();
        saveEmailDataTask.execute(userEmailDTO);
        showMessageSuccess();
        loggedUser.setEmail(edited_email);
        loggedUser.setConfirmed(false);
        updateUser();
        //to do confirmation new mail
    }*/

    public void onClickSavePhoneNumber(View view){
        String area_call_edited = ((TextView)findViewById(R.id.area_call_text_view)).getText().toString();
        String phone_number_edited = ((TextView)findViewById(R.id.phone_number_text_view)).getText().toString();
        UserPhoneNumberDTO userPhoneNumberDTO = new UserPhoneNumberDTO(loggedUser.getEmail(),area_call_edited,phone_number_edited);
        SavePhoneNumberDataTask task = new SavePhoneNumberDataTask();
        task.execute(userPhoneNumberDTO);
        loggedUser.setAreaCall(area_call_edited);
        loggedUser.setPhoneNumber(phone_number_edited);
        updateUser();
        showMessageSuccess();
    }

    private void showMessageSuccess(){
        Context contex = getApplicationContext();
        CharSequence text = getResources().getString(R.string.message_success_changed_data);
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(contex, text, duration);
        toast.show();
    }

    public void onClickSelectImage(View view){
        Intent i = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(i, RESULT_LOAD_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {

            Uri selectedImage = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream);
            byte[] byteArray = stream.toByteArray();
            UserImageDTO userImageDTO = new UserImageDTO(loggedUser.getEmail(), byteArray);
            SaveImageDataTask task = new SaveImageDataTask();
            task.execute(userImageDTO);

            loggedUser.setProfileImage(byteArray);
            updateUser();
            ImageView imageView = (ImageView)findViewById(R.id.profile_image_view);
            imageView.setImageBitmap(bitmap);
        }
    }


    private class SavePersonalDataTask extends AsyncTask<UserPersonalDataDTO, Void,Boolean> {

        @Override
        protected Boolean doInBackground(UserPersonalDataDTO... userPersonalDataDTO) {
            try {
                String apiUrl = Api.apiUrl + "/user/personalData";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                HttpEntity<UserPersonalDataDTO> data = new HttpEntity<>(userPersonalDataDTO[0]);
                ResponseEntity<Boolean> proba = restTemplate.exchange(apiUrl, HttpMethod.PUT,  data, Boolean.class);

                return proba.getBody();
            } catch (Exception ex) {
                Log.e("", ex.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            out.println("Boolean jeeeeeee " + aBoolean.toString());
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

       private class SavePhoneNumberDataTask extends AsyncTask<UserPhoneNumberDTO, Void,Boolean> {

        @Override
        protected Boolean doInBackground(UserPhoneNumberDTO... phoneNumber) {
            try {
                String apiUrl = Api.apiUrl + "/user/phoneNumber";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                HttpEntity<UserPhoneNumberDTO> data = new HttpEntity<>(phoneNumber[0]);
                ResponseEntity<Boolean> proba = restTemplate.exchange(apiUrl, HttpMethod.PUT,  data, Boolean.class);

                return proba.getBody();
            } catch (Exception ex) {
                Log.e("", ex.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            out.println("Boolean jeeeeeee " + aBoolean.toString());
        }
    }

    private class SaveImageDataTask extends AsyncTask<UserImageDTO, Void,Boolean> {

        @Override
        protected Boolean doInBackground(UserImageDTO... userImageDTO) {
            try {
                String apiUrl = Api.apiUrl + "/user/image";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                HttpEntity<UserImageDTO> data = new HttpEntity<>(userImageDTO[0]);
                ResponseEntity<Boolean> proba = restTemplate.exchange(apiUrl, HttpMethod.PUT,  data, Boolean.class);

                return proba.getBody();
            } catch (Exception ex) {
                Log.e("", ex.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            out.println("Boolean jeeeeeee " + aBoolean.toString());
        }
    }

    private class GetUserDataTask extends AsyncTask<Void, Void,User> {

        protected User doInBackground(Void... voids) {
            try {
                String apiUrl = Api.apiUrl + "/user/1";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                User user = (User) restTemplate.getForObject(apiUrl,User.class);
                return user;
            } catch (Exception ex) {
                Log.e("", ex.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(User user) {
            super.onPostExecute(user);
            ImageView imageView = (ImageView) findViewById(R.id.profile_image_view);
            if (user != null && user.getProfileImage() != null && user.getProfileImage().length > 0 ) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(user.getProfileImage(), 0, user.getProfileImage().length);

                imageView.setImageBitmap(bitmap);
            }

        }
    }

    private void updateUser(){
        SharedPreferences preferences = getApplicationContext().getSharedPreferences(Api.baseName, MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString("userJson", (new Gson()).toJson(loggedUser));
        edit.apply();
    }

}
