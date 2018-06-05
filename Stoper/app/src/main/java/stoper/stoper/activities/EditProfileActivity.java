package stoper.stoper.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.text.Line;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import stoper.stoper.MainActivity;
import stoper.stoper.R;
import stoper.stoper.model.User;
import stoper.stoper.util.MockData;

public class EditProfileActivity extends AppCompatActivity {

    public static final String EXTRA_SCROLL_TO_ELEMENT = Integer.toString(R.id.first_name_text_view);
    private static int RESULT_LOAD_IMAGE = 1;

    private MockData mockData;
    private EditText scrollTo;
    private NestedScrollView scrollView;

    String gender = "";
    String firstName = "";
    String lastName = "";
    String birthYear = "";
    String biography = "";
    String email = "";
    String areaNumber = "";
    String phoneNumber = "";
    private User loggedUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.edit_profile_app_bar_title);

        TextView genderView = (TextView) findViewById(R.id.gender_text_view);
        final List<String> gender_array = Arrays.asList(getResources().getStringArray(R.array.gender_array));
        genderView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditProfileActivity.this);
                builder
                        .setItems(R.array.gender_array, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                ((TextView) findViewById(R.id.gender_text_view)).setText(gender_array.get(which));
                            }
                        });
                builder.show();
            }
        });
        final TextView year_text = (TextView) findViewById(R.id.birth_year_text_view);
        ArrayList<String> years = new ArrayList<>();
        int maxYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = maxYear - 18; i > maxYear - 90; i--) {
            years.add(Integer.toString(i));
        }
        final CharSequence[] charSequenceItems = years.toArray(new CharSequence[years.size()]);
        year_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditProfileActivity.this);
                builder.setItems(charSequenceItems, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        year_text.setText(charSequenceItems[i]);
                    }
                });
                builder.show();
            }
        });

        if (savedInstanceState != null) {
            ((TextView) findViewById(R.id.gender_text_view)).setText(savedInstanceState.getString("gender"));
            ((TextView) findViewById(R.id.first_name_text_view)).setText(savedInstanceState.getString("firstName"));
            ((TextView) findViewById(R.id.last_name_text_view)).setText(savedInstanceState.getString("lastName"));
            ((TextView) findViewById(R.id.birth_year_text_view)).setText(savedInstanceState.getString("birthYear"));
            ((TextView) findViewById(R.id.biography_text_view)).setText(savedInstanceState.getString("biography"));
            ((TextView) findViewById(R.id.email_text_view)).setText(savedInstanceState.getString("email_text"));
            ((TextView) findViewById(R.id.area_call_text_view)).setText(savedInstanceState.getString("area_number"));
            ((TextView) findViewById(R.id.phone_number_text_view)).setText(savedInstanceState.getString("phone_number"));
        } else {
            int focusElement = (int) getIntent().getExtras().get(EXTRA_SCROLL_TO_ELEMENT);
            EditText b = (EditText) findViewById(focusElement);
            b.requestFocus();
            ;
            mockData = (MockData) getApplicationContext();
            loggedUser = mockData.UsersDatabase().get(0);
            if (loggedUser.getGender() == 0) {
                ((TextView) findViewById(R.id.gender_text_view)).setText("Muski");

            } else {
                ((TextView) findViewById(R.id.gender_text_view)).setText("Zenski");
            }
            ((TextView) findViewById(R.id.first_name_text_view)).setText(loggedUser.getFirstName());
            ((TextView) findViewById(R.id.last_name_text_view)).setText(loggedUser.getLastName());
            ((TextView) findViewById(R.id.birth_year_text_view)).setText(Integer.toString(loggedUser.getYearOfBirth()));
            ((TextView) findViewById(R.id.biography_text_view)).setText(loggedUser.getBiography());
            ((TextView) findViewById(R.id.email_text_view)).setText(loggedUser.getEmail());
            ((TextView) findViewById(R.id.area_call_text_view)).setText(loggedUser.getAreaCall());
            ((TextView) findViewById(R.id.phone_number_text_view)).setText(loggedUser.getPhoneNumber());
        }

        ImageView imageView = (ImageView) findViewById(R.id.profile_image_view);
        if (loggedUser.getProfileImage() != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(loggedUser.getProfileImage(), 0, loggedUser.getProfileImage().length);

            imageView.setImageBitmap(bitmap);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String gender_text = ((TextView)findViewById(R.id.gender_text_view)).getText().toString();
        String first_name_text = ((TextView)findViewById(R.id.first_name_text_view)).getText().toString();
        String last_name_text = ((TextView)findViewById(R.id.last_name_text_view)).getText().toString();
        String birth_year_text = ((TextView)findViewById(R.id.birth_year_text_view)).getText().toString();
        String biography_text = ((TextView)findViewById(R.id.biography_text_view)).getText().toString();
        String email_text = ((TextView)findViewById(R.id.email_text_view)).getText().toString();
        String area_number_text = ((TextView)findViewById(R.id.area_call_text_view)).getText().toString();
        String phone_number_text = ((TextView)findViewById(R.id.phone_number_text_view)).getText().toString();
        outState.putString("gender", gender_text);
        outState.putString("firstName",first_name_text);
        outState.putString("lastName",last_name_text);
        outState.putString("birthYear", birth_year_text);
        outState.putString("biography",biography_text);
        outState.putString("email_text",email_text);
        outState.putString("area_number", area_number_text);
        outState.putString("phone_number", phone_number_text);
    }

    public void onClickSaveDataForProfil(View view){
        String edited_gender = ((TextView)findViewById(R.id.gender_text_view)).getText().toString();
        String edited_first_name = ((TextView)findViewById(R.id.first_name_text_view)).getText().toString();
        String edited_last_name = ((TextView)findViewById(R.id.last_name_text_view)).getText().toString();
        String edited_birth_year = ((TextView)findViewById(R.id.birth_year_text_view)).getText().toString();
        String edited_biography = ((TextView)findViewById(R.id.biography_text_view)).getText().toString();
    }

    public void onClickSaveEmail(View view){
        String edited_email = ((TextView)findViewById(R.id.email_text_view)).getText().toString();
        mockData.UsersDatabase().get(0).setEmail(edited_email);

        showMessageSuccess();
        //to do confirmation new mail
    }

    public void onClickSavePhoneNumber(View view){
        String area_call_edited = ((TextView)findViewById(R.id.area_call_text_view)).getText().toString();
        String phone_number_edited = ((TextView)findViewById(R.id.phone_number_text_view)).getText().toString();
        mockData.UsersDatabase().get(0).setAreaCall(area_call_edited);
        mockData.UsersDatabase().get(0).setPhoneNumber(phone_number_edited);

        showMessageSuccess();
    }

    private void showMessageSuccess(){
        Context contex = getApplicationContext();
        CharSequence text = "Uspešno se izvršili izmenu Vaših podataka";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(contex, text, duration);
        toast.show();
    }

    public void onClickSelectImage(View view){
        Intent i = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(i, RESULT_LOAD_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {

            Uri selectedImage = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream);
            byte[] byteArray = stream.toByteArray();
            User user = mockData.UsersDatabase().get(0);
            user.setProfileImage(byteArray);
            ImageView imageView = (ImageView)findViewById(R.id.profile_image_view);
            imageView.setImageBitmap(bitmap);
        }
    }
}
