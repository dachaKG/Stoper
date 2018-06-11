package stoper.stoper.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import stoper.stoper.Api;
import stoper.stoper.DTO.UserCustomSettingsDTO;
import stoper.stoper.R;
import stoper.stoper.model.User;
import stoper.stoper.util.MockData;

import static java.lang.System.out;

public class UserCustomSettingsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    //private MockData mockData;
    private int selectedSmoking = 1;
    private int selectedSpeaking = 1;
    private int selectedPets = 1;
    private int selectedMusic = 1;
    private User user;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_custom_settings);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.user_custom_settings_app_bar_title);

        preferences = getApplicationContext().getSharedPreferences(Api.baseName, MODE_PRIVATE);
        user =  new Gson().fromJson(preferences.getString("userJson", ""), User.class);

        Spinner spinner = (Spinner) findViewById(R.id.speaking_spinner);
        spinner.setOnItemSelectedListener(this);
        spinner.setLayoutMode(Spinner.MODE_DIALOG);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.speaking_array, R.layout.spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Spinner spinner_smoking = (Spinner) findViewById(R.id.smoking_spinner);
        spinner_smoking.setOnItemSelectedListener(this);
        spinner_smoking.setLayoutMode(Spinner.MODE_DIALOG);
        ArrayAdapter<CharSequence> adapter_smoking  = ArrayAdapter.createFromResource(this,R.array.smoking_array, R.layout.spinner_item);
        adapter_smoking.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_smoking.setAdapter(adapter_smoking);

        Spinner spinner_music = (Spinner) findViewById(R.id.music_spinner);
        spinner_music.setOnItemSelectedListener(this);
        spinner_music.setLayoutMode(Spinner.MODE_DIALOG);
        ArrayAdapter<CharSequence> adapter_music  = ArrayAdapter.createFromResource(this,R.array.music_array, R.layout.spinner_item);
        adapter_music.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_music.setAdapter(adapter_music);

        Spinner spinner_pets = (Spinner) findViewById(R.id.pets_spinner);
        spinner_pets.setOnItemSelectedListener(this);
        spinner_pets.setLayoutMode(Spinner.MODE_DIALOG);
        ArrayAdapter<CharSequence> adapter_pets  = ArrayAdapter.createFromResource(this,R.array.pets_array, R.layout.spinner_item);
        adapter_pets.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_pets.setAdapter(adapter_pets);

        if(savedInstanceState != null){
            selectedSpeaking = savedInstanceState.getInt("speaking");
            selectedSmoking = savedInstanceState.getInt("smoking");
            selectedMusic = savedInstanceState.getInt("music");
            selectedPets = savedInstanceState.getInt("pets");
        }
        else{
            if (user.getSpeaking() != null) {
                selectedSpeaking = user.getSpeaking();
            }
            if (user.getSmoking() != null) {
                selectedSmoking = user.getSmoking();
            }

            if (user.getMusic() != null) {
                selectedMusic = user.getMusic();
            }
            if (user.getPets() != null) {
                selectedPets = user.getPets();
            }
        }

        spinner.setSelection(selectedSpeaking);
        spinner_pets.setSelection(selectedPets);
        spinner_music.setSelection(selectedMusic);
        spinner_smoking.setSelection(selectedSmoking);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("music",selectedMusic);
        outState.putInt("speaking",selectedSpeaking);
        outState.putInt("smoking",selectedSmoking);
        outState.putInt("pets",selectedPets);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()){
            case R.id.speaking_spinner:
                selectedSpeaking = i;
                break;
            case R.id.smoking_spinner:
                selectedSmoking = i;
                break;
            case R.id.music_spinner:
                selectedMusic = i;
                break;
            case R.id.pets_spinner:
                selectedPets = i;
                break;
        }
    }

    public void onClickSaveCustomSettings(View view){
        user.setSpeaking(selectedSpeaking);
        user.setSmoking(selectedSmoking);
        user.setMusic(selectedMusic);
        user.setPets(selectedPets);
        UserCustomSettingsDTO userCustomSettingsDTO = new UserCustomSettingsDTO(user.getEmail(),selectedSpeaking,selectedSmoking,selectedMusic,selectedPets);
        SaveCustomSettingsDataTask task = new SaveCustomSettingsDataTask();
        task.execute(userCustomSettingsDTO);
        updateUser();
        showMessageSuccess();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void showMessageSuccess(){
        Context contex = getApplicationContext();
        CharSequence text = "Uspešno se izvršili izmenu Vaših podataka";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(contex, text, duration);
        toast.show();
    }

    private class SaveCustomSettingsDataTask extends AsyncTask<UserCustomSettingsDTO, Void,Boolean> {

        @Override
        protected Boolean doInBackground(UserCustomSettingsDTO... customSettingsDTO) {
            try {
                String apiUrl = Api.apiUrl + "/user/customSettings";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                HttpEntity<UserCustomSettingsDTO> data = new HttpEntity<>(customSettingsDTO[0]);
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

    private void updateUser(){
        SharedPreferences preferences = getApplicationContext().getSharedPreferences(Api.baseName, MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString("userJson", (new Gson()).toJson(user));
        edit.apply();
    }
}
