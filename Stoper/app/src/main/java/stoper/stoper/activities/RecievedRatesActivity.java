package stoper.stoper.activities;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
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

public class RecievedRatesActivity extends AppCompatActivity {

    private User loggedUser;
    private SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recieved_rates);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.recieved_rates_app_bar_title);
        preferences = getApplicationContext().getSharedPreferences(Api.baseName, MODE_PRIVATE);
        loggedUser =  new Gson().fromJson(preferences.getString("userJson", ""), User.class);
         /*RecyclerView recyclerView = findViewById(R.id.recieved_rates_recycler);

        //List<RateDTO> rates = mockData.RatesDatabase();
       RecievedRateAdapter adapter = new RecievedRateAdapter(rates);
        recyclerView.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);*/
        TaskGetByRecieverId task = new TaskGetByRecieverId(this);
        task.execute(loggedUser.getId());
    }

    private class TaskGetByRecieverId extends AsyncTask<Long, Void, RateDTO[]> {

        public RecievedRatesActivity activity;

        public TaskGetByRecieverId(RecievedRatesActivity activityRates){
            this.activity = activityRates;
        }
        @Override
        protected RateDTO[] doInBackground(Long... ids) {
            try {
                String apiUrl = Api.apiUrl + "/rate/reciever/" + ids[0];
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
            RecyclerView recyclerView = findViewById(R.id.recieved_rates_recycler);
            RecievedRateAdapter adapter = new RecievedRateAdapter(Arrays.asList(rates));
            recyclerView.setAdapter(adapter);
            GridLayoutManager layoutManager = new GridLayoutManager(activity, 1);
            recyclerView.setLayoutManager(layoutManager);
            int count_perfect = 0;
            int  count_excellent = 0;
            int count_good = 0;
            int count_bad = 0;
            int count_disappointing = 0;

            for (RateDTO r : rates){
                if (r.getMark().equals("Izvanredno")){
                    count_perfect++;
                }else if (r.getMark().equals("Odlično")){
                    count_excellent++;
                }else if(r.getMark().equals("Dobro")){
                    count_good++;
                }else if(r.getMark().equals("Loše")){
                    count_bad++;
                }else if (r.getMark().equals("Veoma razočaravajuće")){
                    count_disappointing++;
                }
            }

            TextView perfect = activity.findViewById(R.id.perfect_rates_count);
            perfect.setText(String.valueOf(count_perfect));

            TextView excellent = activity.findViewById(R.id.excellent_rates_count);
            excellent.setText(String.valueOf(count_excellent));

            TextView good = activity.findViewById(R.id.good_rates_count);
            good.setText(String.valueOf(count_good));

            TextView bad = activity.findViewById(R.id.bad_rates_count);
            bad.setText(String.valueOf(count_bad));

            TextView disappointing = activity.findViewById(R.id.very_disappointing_rates_count);
            disappointing.setText(String.valueOf(count_disappointing));
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
