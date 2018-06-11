package stoper.stoper.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.List;

import stoper.stoper.DTO.RateDTO;
import stoper.stoper.R;
import stoper.stoper.adapter.RateAdapter;
import stoper.stoper.adapter.RecievedRateAdapter;
import stoper.stoper.util.MockData;

public class RecievedRatesActivity extends AppCompatActivity {

    MockData mockData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recieved_rates);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.recieved_rates_app_bar_title);

        RecyclerView recyclerView = findViewById(R.id.recieved_rates_recycler);

        List<RateDTO> rates = mockData.RatesDatabase();
        RecievedRateAdapter adapter = new RecievedRateAdapter(rates);
        recyclerView.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
    }
}
