package stoper.stoper.activities;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.Gson;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import stoper.stoper.Api;
import stoper.stoper.DTO.RateDTO;
import stoper.stoper.R;
import stoper.stoper.adapter.RateAdapter;
import stoper.stoper.adapter.RecievedRateAdapter;
import stoper.stoper.model.User;
import stoper.stoper.util.MockData;

public class StatisticDataActivity extends AppCompatActivity {

    //sMockData mockData;
    private User loggedUser;
    private SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic_data);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.statistic_data_app_bar_title);
        preferences = getApplicationContext().getSharedPreferences(Api.baseName, MODE_PRIVATE);
        loggedUser =  new Gson().fromJson(preferences.getString("userJson", ""), User.class);

        /*RecyclerView recyclerView = findViewById(R.id.added_rates_recycler);

        List<RateDTO> rates = mockData.RatesDatabase();
        RateAdapter adapter = new RateAdapter(rates);
        recyclerView.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);*/

        TaskGetByEvaluatorId task = new TaskGetByEvaluatorId(this);
        task.execute(loggedUser.getId());
    }


    private class TaskGetByEvaluatorId extends AsyncTask<Long, Void, RateDTO[]> {

        public StatisticDataActivity activity;

        public TaskGetByEvaluatorId(StatisticDataActivity activity){
            this.activity = activity;
        }
        @Override
        protected RateDTO[] doInBackground(Long... ids) {
            try {
                String apiUrl = Api.apiUrl + "/rate/evaluator/" + ids[0];
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                RateDTO[] proba = restTemplate.getForObject(apiUrl, RateDTO[].class);

                return proba;
            } catch (Exception ex) {
                Log.e("", ex.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(RateDTO[] rates) {
            super.onPostExecute(rates);
            RecyclerView recyclerView = findViewById(R.id.added_rates_recycler);
            RateAdapter adapter = new RateAdapter(Arrays.asList(rates));
            recyclerView.setAdapter(adapter);
            GridLayoutManager layoutManager = new GridLayoutManager(activity, 1);
            recyclerView.setLayoutManager(layoutManager);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
