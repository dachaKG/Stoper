package stoper.stoper.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import  stoper.stoper.R;
import stoper.stoper.model.User;
import stoper.stoper.util.MockData;

public class ChangePasswordActivity extends AppCompatActivity {

    MockData mockData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.change_password_app_tar_title);
    }

    public void onClickChangePassword(View view){
        User user= mockData.UsersDatabase().get(0);
        CharSequence charSequenceOld = ((EditText)findViewById(R.id.change_password_old)).getText();
        CharSequence charSequenceNew = ((EditText)findViewById(R.id.change_password_new)).getText();
        CharSequence charSequenceNewConfirmed = ((EditText)findViewById(R.id.change_password_new_confirmed)).getText();

        if(!charSequenceNew.equals(charSequenceNewConfirmed) || !charSequenceOld.equals(user.getPassword())){
            return;
        }

        user.setPassword(charSequenceNew.toString());



    }
}
