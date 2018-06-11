package stoper.stoper.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

import stoper.stoper.Api;
import stoper.stoper.R;
import stoper.stoper.chat.model.ChatUser;
import stoper.stoper.chat.ui.activity.ChatActivity;
import stoper.stoper.chat.utils.Constants;

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

    public void onClickChatDriver(View view){
        SharedPreferences loggedUserDetails;

        loggedUserDetails = getApplicationContext().getSharedPreferences(Api.baseName, MODE_PRIVATE);

        getUserFromFirebase(loggedUserDetails.getString("drive_email", ""), this);
    }

    public void getUserFromFirebase(final String email, final Context context) {
        //ChatUser chatUser = new ChatUser();
        FirebaseDatabase.getInstance().getReference().child(Constants.ARG_USERS).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> dataSnapshots = dataSnapshot.getChildren().iterator();

                ChatUser chatUser = new ChatUser();
                while (dataSnapshots.hasNext()) {
                    DataSnapshot dataSnapshotChild = dataSnapshots.next();

                    ChatUser user = dataSnapshotChild.getValue(ChatUser.class);

                    if (user.email.toString().equals(email)) {
                        chatUser = user;
                        break;
                    }
                }
                ChatActivity.startActivity(context,
                        chatUser.email,
                        chatUser.uid,
                        chatUser.firebaseToken);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
