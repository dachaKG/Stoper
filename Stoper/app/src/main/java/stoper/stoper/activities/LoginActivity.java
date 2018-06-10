package stoper.stoper.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import android.util.Log;

import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;


import stoper.stoper.Api;
import stoper.stoper.R;
import stoper.stoper.model.LoginReq;
import stoper.stoper.model.User;
import stoper.stoper.model.User;


import static android.content.Context.MODE_PRIVATE;
import static java.lang.System.out;

import android.support.v7.app.AppCompatActivity;

import java.security.AccessController;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    public EditText usernameText = null;
    public EditText passwordText = null;
    public TextView counterText = null;
    public Intent intentt;
    public String usernameArg = " ";
    public String passwordArg = " ";

    public Button registerButton;
    public Button loginButton;
    int counter = 3;
    Bundle bundle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        intentt = new Intent(LoginActivity.this, NavigationActivity.class);

    }


    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        usernameText = view.findViewById(R.id.usernameID);
        passwordText = view.findViewById(R.id.passwordID);


        registerButton = view.findViewById(R.id.register);

        loginButton = view.findViewById(R.id.login);


    }


    private class HttpReqTask extends AsyncTask<LoginReq, Void, User> {


        @Override
        protected User doInBackground(LoginReq... users) {
            try {
                String apiUrl = Api.apiUrl + "/user/login";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                HttpEntity<LoginReq> user = new HttpEntity<>(users[0]);
                ResponseEntity<User> userTest = restTemplate.exchange(apiUrl, HttpMethod.POST, user, User.class);
                return userTest.getBody();
            } catch (Exception ex) {
                Log.e("..", ex.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(User userLoged) {
            super.onPostExecute(userLoged);

            if (userLoged == null) {
                System.out.println("Nema tog korisnika u bazi");
            } else {
                Context context;


                SharedPreferences loggedUserDetails;

                loggedUserDetails = getApplicationContext().getSharedPreferences(Api.baseName, MODE_PRIVATE);

                SharedPreferences.Editor edit = loggedUserDetails.edit();
                edit.putString("firstName", userLoged.getFirstName());
                edit.putString("lastname", userLoged.getLastName());
                edit.putString("email", userLoged.getEmail());
                edit.putInt("gender", userLoged.getGender());

                edit.apply();

                loggedUserDetails = getApplicationContext().getSharedPreferences(Api.baseName, MODE_PRIVATE);

                String userName = loggedUserDetails.getString("firstName", "");


                System.out.println("ulogovan user je - " + userName);
                intentt = new Intent(LoginActivity.this, NavigationActivity.class);
                startActivity(intentt);


            }
        }
    }


    public void login(View view) {
        usernameText = findViewById(R.id.usernameID);
        passwordText = findViewById(R.id.passwordID);


        LoginReq user = new LoginReq();
        usernameArg = usernameText.getText().toString().trim();
        passwordArg = passwordText.getText().toString().trim();

        System.out.println("reagovao na klik jb");
        System.out.println(usernameArg);
        System.out.println(passwordArg);
        try {

            user.setEmail(usernameArg);
            user.setPassword(passwordArg);
            Gson gson = new Gson();
            String json = gson.toJson(user);
            System.out.println(json);


            new HttpReqTask().execute(user);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void redirectToRegistration(View view) {
        intentt = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intentt);
    }

}