package stoper.stoper.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import stoper.stoper.Api;
import stoper.stoper.DTO.UserCarDTO;
import stoper.stoper.DTO.UserPasswordDTO;
import  stoper.stoper.R;
import stoper.stoper.model.User;
import stoper.stoper.util.MockData;

import static java.lang.System.out;

public class ChangePasswordActivity extends AppCompatActivity {

    private User user;
    private SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.change_password_app_tar_title);

        preferences = getApplicationContext().getSharedPreferences(Api.baseName, MODE_PRIVATE);
        user =  new Gson().fromJson(preferences.getString("userJson", ""), User.class);
    }

    public void onClickChangePassword(View view){
        String charSequenceOld = ((EditText)findViewById(R.id.change_password_old)).getText().toString();
        String charSequenceNew = ((EditText)findViewById(R.id.change_password_new)).getText().toString();
        String charSequenceNewConfirmed = ((EditText)findViewById(R.id.change_password_new_confirmed)).getText().toString();

        if(!charSequenceNew.equals(charSequenceNewConfirmed) || !charSequenceOld.equals(user.getPassword())){
            return;
        }
        UserPasswordDTO userPasswordDTO = new UserPasswordDTO(user.getEmail(),charSequenceOld,charSequenceNew,charSequenceNewConfirmed);
        SavePasswordDataTask task = new SavePasswordDataTask();
        task.execute(userPasswordDTO);

        user.setPassword(charSequenceNew.toString());
        updateUser();
        showMessageSuccess();
    }

    private class SavePasswordDataTask extends AsyncTask<UserPasswordDTO, Void,Boolean> {

        @Override
        protected Boolean doInBackground(UserPasswordDTO... passwordDTOS) {
            try {
                String apiUrl = Api.apiUrl + "/user/password";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                HttpEntity<UserPasswordDTO> data = new HttpEntity<>(passwordDTOS[0]);
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

    private void showMessageSuccess(){
        Context contex = getApplicationContext();
        CharSequence text = getResources().getString(R.string.message_success_changed_data);
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(contex, text, duration);
        toast.show();
    }
}
