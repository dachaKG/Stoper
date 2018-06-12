package stoper.stoper.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.google.gson.Gson;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import stoper.stoper.Api;
import stoper.stoper.DTO.RateDTO;
import stoper.stoper.R;
import stoper.stoper.model.LoginReq;
import stoper.stoper.model.RegistrationReq;

import static java.lang.System.out;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.support.v7.app.AlertDialog;
import android.widget.LinearLayout;

import stoper.stoper.R;

public class CommentActivity extends AppCompatActivity {


    public Boolean validInputs=true;
    public EditText commentEditText;
	public EditText marksEditText;
    public Intent intentt;
   
    public static  String mailSenderArg = " ";
	public static  String mailReceivcerArg = " ";
	public static  String commentArg = " ";
	public static String markArg=" ";

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comment);
		
		marksEditText=findViewById(R.id.marks);
        commentEditText=findViewById(R.id.comment);
		
	}

    public void openDialog(View view) {

        marksEditText=findViewById(R.id.marks);
        final CharSequence marks[] = new CharSequence[]	{"Izvanredno", "Odlično", "Dobro","Loše","Veoma razočaravajuće"};

        AlertDialog.Builder builder = new AlertDialog.Builder(CommentActivity.this);
        builder.setItems(marks, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                marksEditText.setText(marks[which]);
                TextInputLayout til = findViewById(R.id.textInputLayout1);
                til.setErrorEnabled(false);
            }
        });
        builder.show();
    }

	
	public void submitMarks(View view){
		checkFields();
		
		RateDTO rate=new RateDTO();
		
		try {

            if(validInputs){
                Date dateNow=new Date();
                System.out.println("Polja ok");
                commentArg=commentEditText.getText().toString().trim();
                markArg=marksEditText.getText().toString().trim();
				mailReceivcerArg="d@gmail.com";
				mailSenderArg="s@gmail.com";
				//dodaj datum


				
                SharedPreferences loggedUserDetails;
                loggedUserDetails = getApplicationContext().getSharedPreferences(Api.baseName, MODE_PRIVATE);

                String userName = loggedUserDetails.getString("email", "");

                Intent intent = getIntent();
                Bundle extras = getIntent().getExtras();

                mailReceivcerArg= getIntent().getExtras().getString("korisnikZaOcenjivanje");

                //Toast.makeText(getApplicationContext(), mailReceivcerArg, Toast.LENGTH_SHORT).show();
                rate.setRecieverEmail(mailReceivcerArg);
				rate.setEvaluatorEmail(userName);
				rate.setMark(markArg);
				rate.setComment(commentArg);
				//na backu namesti trenutni datum i user-a dva na osnovu email adresa...
				
				
                Gson gson = new Gson();
                String json = gson.toJson(rate);
                System.out.println(json);
                new CommentActivity.HttpReqTask().execute(rate);
            }
            else{
                System.out.println("NOT OK NOT OK NOT OK");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

		
	}

    private class HttpReqTask extends AsyncTask<RateDTO, Void, Boolean> {

        @Override
        protected Boolean doInBackground(RateDTO... rates) {
            try {
                String apiUrl = Api.apiUrl + "/rate/rateRide";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                HttpEntity<RateDTO> rate = new HttpEntity<>(rates[0]);
                ResponseEntity<Boolean> response = restTemplate.exchange(apiUrl, HttpMethod.PUT,  rate, Boolean.class);
                return response.getBody();
            } catch (Exception ex) {
                Log.e("..", ex.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if(aBoolean) {

                intentt = new Intent(CommentActivity.this, LoginActivity.class);
                startActivity(intentt);
            }
            else{
                out.println("Failed");
            }
        }
    }


    public boolean checkFields(){
 
        if(marksEditText.getText().length()==0){
            TextInputLayout til1 = findViewById(R.id.textInputLayout1);
            til1.setError("Ovo polje je obavezno");
            validInputs=false;
            return false;
        }else {
            validInputs=true;
        }

        return true;
    }



}

