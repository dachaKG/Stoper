package stoper.stoper.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import stoper.stoper.R;
public class SuccessReserveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_reserve);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle(R.string.success_reservation_title);
    }

    public void onClickOpenHomePage(View view){
        Intent intent = new Intent(SuccessReserveActivity.this, NavigationActivity.class);
        startActivity(intent);
    }
}
