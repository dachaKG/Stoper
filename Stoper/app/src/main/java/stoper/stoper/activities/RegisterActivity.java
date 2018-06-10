package stoper.stoper.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import stoper.stoper.Api;
import stoper.stoper.R;
import stoper.stoper.chat.core.registration.RegisterContract;
import stoper.stoper.chat.core.registration.RegisterPresenter;
import stoper.stoper.chat.core.users.add.AddUserContract;
import stoper.stoper.chat.core.users.add.AddUserPresenter;
import stoper.stoper.model.LoginReq;
import stoper.stoper.model.RegistrationReq;

import static java.lang.System.out;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.support.v7.app.AlertDialog;
import android.widget.LinearLayout;

import stoper.stoper.R;

import static java.security.AccessController.getContext;

public class RegisterActivity  extends AppCompatActivity implements  RegisterContract.View, AddUserContract.View {

    public Boolean validInputs=true; // polje ko je ce govoriti da li su sva polja popunjena
    //i da li su popunjena kako treba
    public Button submitRegButton;
    // TODO: Rename and change types of parameters
    public Button register_with_mail_button;
    public LinearLayout ll;
    public EditText genderEditText;
    public EditText birthYearEditText;
    public EditText nameEditText;
    public EditText lastnameEditText;
    public EditText passwordEditText;
    public EditText repeatPaswordEditText;
    public EditText mailEditText;
    public Intent intentt;
    //public OnFragmentInteractionListener mListener;


    public static  String nameArg = " ";
    public static  String  lastnameArg = " ";
    public static  int genderArg = 0;
    public static  String  emailArg = " ";
    public static  int yearOfBirthArg = 0;
    public static  String  passwordArg = " ";
    public static  String usernameArg = " ";


    Bundle bundle;

    private RegisterPresenter mRegisterPresenter;
    private AddUserPresenter mAddUserPresenter;
    private ProgressDialog mProgressDialog;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register_with_mail_button = findViewById(R.id.register_with_mail);
        register_with_mail_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext(), "okolo svuda je tama", Toast.LENGTH_LONG).show();
                if(ll.getVisibility()==View.GONE)
                    ll.setVisibility(View.VISIBLE);
                else{/*
                    if(checkFields()){
                        Toast.makeText(v.getContext(), "dobro je", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(v.getContext(), "Nije dobro", Toast.LENGTH_LONG).show();
                    }*/

                }
            }
        });
        ll=findViewById(R.id.popup);
        ll.setVisibility(View.GONE);


        /**
         * Gender choice
         */

        genderEditText=findViewById(R.id.gender);
        genderEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence genders[] = new CharSequence[] {"Muski", "Zenski"};

                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                builder.setItems(genders, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // the user clicked on colors[which]
                        genderEditText.setText(genders[which]);
                        TextInputLayout til = findViewById(R.id.textInputLayout1);
                        til.setErrorEnabled(false);
                    }
                });
                builder.show();
            }
        });

        /**
         * Birth year choice
         */
        birthYearEditText=findViewById(R.id.birth_year);
        birthYearEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<String> years=new ArrayList<>();
                int maxYear=Calendar.getInstance().get(Calendar.YEAR);
                for(int i=maxYear-18;i>maxYear-90;i--)
                {
                    years.add(Integer.toString(i));
                }

                final CharSequence[] charSequenceItems = years.toArray(new CharSequence[years.size()]);
                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                builder.setItems(years.toArray(new CharSequence[years.size()]), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // the user clicked on colors[which]
                        birthYearEditText.setText(charSequenceItems[which]);
                        TextInputLayout til = findViewById(R.id.textInputLayout4);
                        til.setErrorEnabled(false);
                    }
                });
                builder.show();
            }
        });

        mRegisterPresenter = new RegisterPresenter(this);
        mAddUserPresenter = new AddUserPresenter(this);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle(getString(R.string.loading));
        mProgressDialog.setMessage(getString(R.string.please_wait));
        mProgressDialog.setIndeterminate(true);



    }



    public void register(View view) {

        RegistrationReq user = new RegistrationReq();
        checkFields();


        try {

            if(validInputs){
                nameArg=nameEditText.getText().toString().trim();
                usernameArg=mailEditText.getText().toString().trim();
                lastnameArg=lastnameEditText.getText().toString().trim();
                emailArg=mailEditText.getText().toString().trim();
                passwordArg=passwordEditText.getText().toString().trim();
                yearOfBirthArg=Integer.valueOf(birthYearEditText.getText().toString());
                if((genderEditText.getText().toString()).equals("Muski")){
                    genderArg=1;
                }else
                {
                    genderArg=2;
                }

                System.out.println("Polja ok");
                Gson gson = new Gson();
                user.setEmail(usernameArg);
                user.setPassword(passwordArg);
                user.setFirstName(nameArg);
                user.setLastName(lastnameArg);
                user.setYearOfBirth(yearOfBirthArg);
                user.setGender(genderArg);
                user.setPassword(passwordArg);
                String json = gson.toJson(user);
                System.out.println(json);
                mRegisterPresenter.register(this, usernameArg, passwordArg);
                mProgressDialog.show();
                new RegisterActivity.HttpReqTask().execute(user);
            }
            else{
                System.out.println("NOT OK NOT OK NOT OK");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public boolean checkFields(){
        nameEditText= findViewById(R.id.name);
        lastnameEditText=findViewById(R.id.lastname);
        mailEditText=findViewById(R.id.e_mail);
        passwordEditText=findViewById(R.id.password);
        repeatPaswordEditText=findViewById(R.id.repeat_password);
        TextInputLayout til2 = findViewById(R.id.textInputLayout2);
        TextInputLayout til3 = findViewById(R.id.textInputLayout3);
        TextInputLayout til5 = findViewById(R.id.textInputLayout5);
        TextInputLayout til6 = findViewById(R.id.textInputLayout6);
        TextInputLayout til7 = findViewById(R.id.textInputLayout7);
        if(genderEditText.getText().length()==0){
            TextInputLayout til1 = findViewById(R.id.textInputLayout1);
            til1.setError("Ovo polje je obavezno");
            validInputs=false;
            return false;
        }else if(nameEditText.getText().length()==0){

            til2.setError("Ovo polje je obavezno");
            validInputs=false;
            return false;
        }else {
            validInputs=true;
            til2.setErrorEnabled(false);
        }
        if(lastnameEditText.getText().length()==0){
            til3.setError("Ovo polje je obavezno");
            validInputs=false;
            return false;
        }else {
            validInputs=true;
            til3.setErrorEnabled(false);
        }
        if(birthYearEditText.getText().length()==0){
            TextInputLayout til = (TextInputLayout) findViewById(R.id.textInputLayout4);
            til.setError("Ovo polje je obavezno");
            validInputs=false;
            return false;
        }else if(!isEmailValid(mailEditText.getText().toString())){
            til5.setError("Molimo Vas unesite vazecu e-mail adresu");
            validInputs=false;
            return false;
        }else {
            validInputs=true;
            til5.setErrorEnabled(false);
        }
        if(passwordEditText.getText().length()<8){
            til6.setError("Izaberite lozinku od najmanje 8 znakova");
            validInputs=false;
            return false;
        }else {
            validInputs=true;
            til6.setErrorEnabled(false);
        }
        if(!(repeatPaswordEditText.getText().toString().equals(passwordEditText.getText().toString()))){
            til7.setError("Lozinke se moraju podudarati");
            validInputs=false;
            return false;
        }else{
            validInputs=true;
            til7.setErrorEnabled(false);
        }

        return true;
    }


    @Override
    public void onRegistrationSuccess(FirebaseUser firebaseUser) {
        mProgressDialog.setMessage(getString(R.string.adding_user_to_db));
        Toast.makeText(this, "Registration Successful!", Toast.LENGTH_SHORT).show();
        mAddUserPresenter.addUser(this.getApplicationContext(), firebaseUser);
    }

    @Override
    public void onRegistrationFailure(String message) {
        mProgressDialog.dismiss();
        mProgressDialog.setMessage(getString(R.string.please_wait));
        Log.e("ss", "onRegistrationFailure: " + message);
        Toast.makeText(this, "Registration failed!+\n" + message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAddUserSuccess(String message) {
        mProgressDialog.dismiss();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        /*UserListingActivity.startActivity(getActivity(),
                Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);*/
        //Intent intent =
    }

    @Override
    public void onAddUserFailure(String message) {
        mProgressDialog.dismiss();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private class HttpReqTask extends AsyncTask<RegistrationReq, Void, Boolean> {

        @Override
        protected Boolean doInBackground(RegistrationReq... users) {
            try {
                String apiUrl = Api.apiUrl + "/user/register";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                HttpEntity<RegistrationReq> user = new HttpEntity<>(users[0]);

                ResponseEntity<Boolean> userTest = restTemplate.exchange(apiUrl, HttpMethod.PUT,  user, Boolean.class);

                return userTest.getBody();
            } catch (Exception ex) {
                Log.e("..", ex.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if(aBoolean) {
                intentt = new Intent(RegisterActivity.this, LoginActivity.class);

                startActivity(intentt);
            }
            else{
                out.println("Molimo Vas unesite drugu e-mail adresu");
            }
        }
    }


    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}