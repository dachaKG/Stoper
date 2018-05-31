package stoper.stoper.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import stoper.stoper.R;

public class EditProfileActivity extends AppCompatActivity {

    String gender = "";
    String firstName = "";
    String lastName = "";
    String birthYear = "";
    String biography = "";
    String email = "";
    String areaNumber = "";
    String phoneNumber = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.edit_profile_app_bar_title);
        if(savedInstanceState != null){
            ((TextView)findViewById(R.id.gender_text_view)).setText(savedInstanceState.getString("gender"));
            ((TextView)findViewById(R.id.first_name_text_view)).setText(savedInstanceState.getString("firstName"));
            ((TextView)findViewById(R.id.last_name_text_view)).setText(savedInstanceState.getString("lastName"));
            ((TextView)findViewById(R.id.birth_year_text_view)).setText(savedInstanceState.getString("birthYear"));
            ((TextView)findViewById(R.id.biography_text_view)).setText(savedInstanceState.getString("biography"));
            ((TextView)findViewById(R.id.email_text_view)).setText(savedInstanceState.getString("email_text"));
            ((TextView)findViewById(R.id.area_call_text_view)).setText(savedInstanceState.getString("area_number"));
            ((TextView)findViewById(R.id.phone_number_text_view)).setText(savedInstanceState.getString("phone_number"));
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String gender_text = ((TextView)findViewById(R.id.gender_text_view)).getText().toString();
        String first_name_text = ((TextView)findViewById(R.id.first_name_text_view)).getText().toString();
        String last_name_text = ((TextView)findViewById(R.id.last_name_text_view)).getText().toString();
        String birth_year_text = ((TextView)findViewById(R.id.birth_year_text_view)).getText().toString();
        String biography_text = ((TextView)findViewById(R.id.biography_text_view)).getText().toString();
        String email_text = ((TextView)findViewById(R.id.email_text_view)).getText().toString();
        String area_number_text = ((TextView)findViewById(R.id.area_call_text_view)).getText().toString();
        String phone_number_text = ((TextView)findViewById(R.id.phone_number_text_view)).getText().toString();
        outState.putString("gender", gender_text);
        outState.putString("firstName",first_name_text);
        outState.putString("lastName",last_name_text);
        outState.putString("birthYear", birth_year_text);
        outState.putString("biography",biography_text);
        outState.putString("email_text",email_text);
        outState.putString("area_number", area_number_text);
        outState.putString("phone_number", phone_number_text);
    }

    public void onClickSaveDataForProfil(View view){
        String edited_gender = ((TextView)findViewById(R.id.gender_text_view)).getText().toString();
        String edited_first_name = ((TextView)findViewById(R.id.first_name_text_view)).getText().toString();
        String edited_last_name = ((TextView)findViewById(R.id.last_name_text_view)).getText().toString();
        String edited_birth_year = ((TextView)findViewById(R.id.birth_year_text_view)).getText().toString();
        String edited_biography = ((TextView)findViewById(R.id.biography_text_view)).getText().toString();
    }

    public void onClickSaveEmail(View view){
        String edited_email = ((TextView)findViewById(R.id.email_text_view)).getText().toString();

    }

    public void onClickSavePhoneNumber(View view){
        String area_call_edited = ((TextView)findViewById(R.id.area_call_text_view)).getText().toString();
        String phone_number_edited = ((TextView)findViewById(R.id.phone_number_text_view)).getText().toString();
    }
}
