package stoper.stoper.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.GridLayoutManager;

import java.util.List;

import stoper.stoper.DTO.RateDTO;
import stoper.stoper.R;
import stoper.stoper.adapter.RateAdapter;
import stoper.stoper.util.MockData;

public class StatisticDataActivity extends AppCompatActivity {

    MockData mockData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic_data);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.statistic_data_app_bar_title);


        RecyclerView recyclerView = findViewById(R.id.added_rates_recycler);

        List<RateDTO> rates = mockData.RatesDatabase();
        RateAdapter adapter = new RateAdapter(rates);
        recyclerView.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
    }
}
