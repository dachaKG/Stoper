package stoper.stoper.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import stoper.stoper.Api;
import stoper.stoper.DTO.UserCarDTO;
import stoper.stoper.DTO.UserCustomSettingsDTO;
import stoper.stoper.R;
import stoper.stoper.model.User;
import stoper.stoper.util.MockData;

import static java.lang.System.out;

public class CarActivity extends AppCompatActivity implements  AdapterView.OnItemSelectedListener{

    private int selectedCountry;
    private int selectedColor;
    private int selectedCarType;
    private User user;
    private SharedPreferences loggedUserDetails;
    //private MockData mockData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.car_first_page_app_bar_title);

        Spinner spinner = (Spinner) findViewById(R.id.country_spinner);
        spinner.setOnItemSelectedListener(this);
        spinner.setLayoutMode(Spinner.MODE_DIALOG);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.countries, R.layout.spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Spinner color_spinner = (Spinner)findViewById(R.id.color_spinner);
        color_spinner.setOnItemSelectedListener(this);
        color_spinner.setLayoutMode(Spinner.MODE_DIALOG);
        ArrayAdapter<CharSequence> color_adapter = ArrayAdapter.createFromResource(this, R.array.colors, R.layout.spinner_item);
        color_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        color_spinner.setAdapter(color_adapter);

        Spinner car_types_spinner = (Spinner) findViewById(R.id.car_types_spinner);
        car_types_spinner.setOnItemSelectedListener(this);
        car_types_spinner.setLayoutMode(Spinner.MODE_DIALOG);
        ArrayAdapter<CharSequence> car_types_adapter = ArrayAdapter.createFromResource(this,R.array.car_types, R.layout.spinner_item);
        car_types_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        car_types_spinner.setAdapter(car_types_adapter);

        loggedUserDetails = getApplicationContext().getSharedPreferences(Api.baseName, MODE_PRIVATE);
        user =  new Gson().fromJson(loggedUserDetails.getString("userJson", ""), User.class);

        if(savedInstanceState != null){
            selectedCountry = savedInstanceState.getInt("selectedCountry");
            selectedColor = savedInstanceState.getInt("selectedColor");
            selectedCarType = savedInstanceState.getInt("selectedCarType");
            ((EditText)findViewById(R.id.car_edit_registration_number_text)).setText(savedInstanceState.getString("registratioNumber"));
            ((EditText)findViewById(R.id.car_edit_car_brand)).setText(savedInstanceState.getString("carBrand"));
            ((EditText)findViewById(R.id.car_edit_car_brand_model)).setText(savedInstanceState.getString("carBrandModel"));
            ((EditText)findViewById(R.id.car_edit_year_text)).setText(savedInstanceState.getString("carYear"));
        }else{
            if (user.getCarType() != null) {
                selectedCarType = user.getCarType();
            }
            if(user.getCarColor() != null) {
                selectedColor = user.getCarColor();
            }
            if(user.getCarCountry() !=null) {
                selectedCountry = user.getCarCountry();
            }
            if(user.getCarRegistratonNumber() != null) {
                ((EditText) findViewById(R.id.car_edit_registration_number_text)).setText(user.getCarRegistratonNumber());
            }
            if(user.getCarBrand() != null) {
                ((EditText) findViewById(R.id.car_edit_car_brand)).setText(user.getCarBrand());
            }
            if(user.getCarBrandModel() != null) {
                ((EditText) findViewById(R.id.car_edit_car_brand_model)).setText(user.getCarBrandModel());
            }
            if(user.getCarYear() != null && user.getCarYear() != 0) {
                ((EditText) findViewById(R.id.car_edit_year_text)).setText(String.valueOf(user.getCarYear()));
            }
        }

        spinner.setSelection(selectedCountry);
        color_spinner.setSelection(selectedColor);
        car_types_spinner.setSelection(selectedCarType);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("selectedCountry",selectedCountry);
        outState.putInt("selectedColor",selectedColor);
        outState.putInt("selectedCarType",selectedCarType);
        outState.putString("registrationNumber", ((EditText)findViewById(R.id.car_edit_registration_number_text)).getText().toString());
        outState.putString("carBrand", ((EditText)findViewById(R.id.car_edit_car_brand)).getText().toString());
        outState.putString("carBrandModel", ((EditText)findViewById(R.id.car_edit_car_brand_model)).getText().toString());
        outState.putString("carYear",((EditText)findViewById(R.id.car_edit_year_text)).getText().toString());
    }

    public void onClickSaveCar(View view){
        String stringCarYear = ((EditText)findViewById(R.id.car_edit_year_text)).getText().toString();
        int carYear = 0;
        if (tryParseInt(stringCarYear)) {
            carYear = Integer.parseInt(stringCarYear);
        }
        String carRegistration = ((EditText)findViewById(R.id.car_edit_registration_number_text)).getText().toString();
        String carBrand = ((EditText)findViewById(R.id.car_edit_car_brand)).getText().toString();
        String carBrandModel = ((EditText)findViewById(R.id.car_edit_car_brand_model)).getText().toString();
        UserCarDTO carDTO = new UserCarDTO(user.getEmail(), selectedCountry, selectedColor, selectedCarType, carRegistration,
                carBrand, carBrandModel, carYear);
        SaveCarDataTask task = new SaveCarDataTask();
        task.execute(carDTO);
        user.setCarColor(selectedColor);
        user.setCarCountry(selectedCountry);
        user.setCarType(selectedCarType);
        if(carYear != 0){
            user.setCarYear(carYear);
        }
        user.setCarRegistratonNumber(carRegistration);
        user.setCarBrand(carBrand);
        user.setCarBrandModel(carBrandModel);
        SharedPreferences preferences = getApplicationContext().getSharedPreferences(Api.baseName, MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString("userJson", (new Gson()).toJson(user));
        edit.apply();
        showMessageSuccess();

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
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()){
            case R.id.country_spinner:
                selectedCountry = i;
                break;
            case R.id.color_spinner:
                selectedColor = i;
                break;
            case R.id.car_types_spinner:
                selectedCarType = i;
                break;
        }
        selectedCountry= i;
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

    private class SaveCarDataTask extends AsyncTask<UserCarDTO, Void,Boolean> {

        @Override
        protected Boolean doInBackground(UserCarDTO... carDTO) {
            try {
                String apiUrl = Api.apiUrl + "/user/car";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                HttpEntity<UserCarDTO> data = new HttpEntity<>(carDTO[0]);
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

    boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
