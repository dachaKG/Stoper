package stoper.stoper.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import stoper.stoper.R;

public class RegisterActivity  extends AppCompatActivity {

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
}
