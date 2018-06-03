package stoper.stoper.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import stoper.stoper.R;

public class CarActivity extends AppCompatActivity implements  AdapterView.OnItemSelectedListener{

    private int selectedCountry;
    private int selectedColor;
    private int selectedCarType;
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

        if(savedInstanceState != null){
            selectedCountry = savedInstanceState.getInt("selectedCountry");
            selectedColor = savedInstanceState.getInt("selectedColor");
            selectedCarType = savedInstanceState.getInt("selectedCarType");
            ((EditText)findViewById(R.id.car_edit_registration_number_text)).setText(savedInstanceState.getString("registratioNumber"));
            ((EditText)findViewById(R.id.car_edit_car_brand)).setText(savedInstanceState.getString("carBrand"));
            ((EditText)findViewById(R.id.car_edit_car_brand_model)).setText(savedInstanceState.getString("carBrandModel"));
            ((EditText)findViewById(R.id.car_edit_year_text)).setText(savedInstanceState.getInt("carYear"));
        }

        spinner.setSelection(selectedCountry);
        color_spinner.setSelection(selectedColor);
        car_types_spinner.setSelection(selectedCarType);
    }

    public void onClickSaveCar(View view){

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
        outState.putInt("carYear", Integer.parseInt(((EditText)findViewById(R.id.car_edit_year_text)).getText().toString()));
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
}
