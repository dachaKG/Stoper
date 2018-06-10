package stoper.stoper.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import stoper.stoper.Api;
import stoper.stoper.R;
import stoper.stoper.chat.core.logout.LogoutContract;
import stoper.stoper.chat.core.logout.LogoutPresenter;
import stoper.stoper.chat.ui.activity.UserListingActivity;
import stoper.stoper.chat.ui.fragment.UsersFragment;
import stoper.stoper.fragments.DestinationFragment;
import stoper.stoper.fragments.MainFragment;
import stoper.stoper.fragments.OfferFragment;
import stoper.stoper.fragments.PlacesFragment;
import stoper.stoper.fragments.ProfileAccountFragment;
import stoper.stoper.fragments.ProfileDetailsFragment;
import stoper.stoper.fragments.ProfileFragment;
import stoper.stoper.fragments.SearchFragment;
import stoper.stoper.fragments.StarterFragment;
import stoper.stoper.model.LoginReq;
import stoper.stoper.model.User;
import stoper.stoper.util.MockData;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, LogoutContract.View {

    private static boolean sIsChatActivityOpen = false;

    public static boolean isChatActivityOpen() {
        return sIsChatActivityOpen;
    }

    public static void setChatActivityOpen(boolean isChatActivityOpen) {
        NavigationActivity.sIsChatActivityOpen = isChatActivityOpen;
    }
    public static boolean isAppRunning;
    MockData mockData;
    private int activeItem = -1;

    Intent intent;
    private LogoutPresenter mLogoutPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //getApplicationContext().getSharedPreferences(Api.baseName, MODE_PRIVATE)
        if (getApplicationContext().getSharedPreferences(Api.baseName, MODE_PRIVATE).getString("email", "") == "") {
            Intent intent = new Intent(NavigationActivity.this, LoginActivity.class);
            intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Fragment fragment = null;
        if (savedInstanceState == null) {
            fragment = getFragmentToShow(activeItem);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.main_screen, fragment, "starterFragment");
            //ft.addToBackStack(null);
            ft.commit();

        } else {
            activeItem = savedInstanceState.getInt("activeItem");
            fragment = getFragmentToShow(activeItem);
        }
        User user = mockData.UsersDatabase().get(0);
        View headerLayout = navigationView.getHeaderView(0);
        TextView name = (TextView) headerLayout.findViewById(R.id.navigation_header_name);
        name.setText(String.format("%s %s", user.getFirstName(), user.getLastName()));

        TextView email = (TextView) headerLayout.findViewById(R.id.navigation_header_email);
        email.setText(user.getEmail());

        ImageView profileImage = (ImageView) headerLayout.findViewById(R.id.navigation_header_image);
        if (user.getProfileImage() != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(user.getProfileImage(), 0, user.getProfileImage().length);
            profileImage.setImageBitmap(bitmap);
        }
        mLogoutPresenter = new LogoutPresenter(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("activeItem", activeItem);
    }

    @Override
    public void onBackPressed() {
        System.out.println(getSupportFragmentManager().getBackStackEntryCount());
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        activeItem = item.getItemId();
        Fragment fragment = getFragmentToShow(id);

        if (fragment != null) {

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.main_screen, fragment, "starterFragment");
            ft.addToBackStack(null);
            ft.commit();
        }

        item.setChecked(true);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.findViewById(R.id.nav_view);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private Fragment getFragmentToShow(int id) {
        Fragment fragment = null;
        switch (id) {
            case R.id.nav_camera:
                fragment = new MainFragment();
                getSupportActionBar().setTitle(R.string.app_bar_offer);
                break;
            case R.id.nav_gallery:
                fragment = new SearchFragment();
                getSupportActionBar().setTitle(R.string.app_bar_search);
                //Toast.makeText(NavigationActivity.this, item.getTitle(), Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_slideshow:
                fragment = new OfferFragment();
                getSupportActionBar().setTitle(R.string.app_bar_demand);
                //Toast.makeText(NavigationActivity.this, item.getTitle(), Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_profile:
                fragment = new ProfileFragment();
                getSupportActionBar().setTitle(R.string.app_bar_profile);
                break;
            case R.id.nav_chat:

               // UserListingActivity.startActivity(this, Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
               /* fragment = new UsersFragment();
                getSupportActionBar().setTitle(R.string.app_bar_chat);*/
                intent = new Intent(this, UserListingActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_logout:

                logout();


           /* case R.id.nav_share:
                break;
            case R.id.nav_send:
                break;*/
            default:
                fragment = new StarterFragment();
                getSupportActionBar().setTitle(R.string.app_bar_home);
        }
        return fragment;
    }


    private void logout() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.menu_logout)
                .setMessage(R.string.are_you_sure)
                .setPositiveButton(R.string.menu_logout, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        mLogoutPresenter.logout();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    @Override
    public void onLogoutSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        SharedPreferences loggedUserDetails = getApplicationContext().getSharedPreferences(Api.baseName, MODE_PRIVATE);

        SharedPreferences.Editor edit = loggedUserDetails.edit();
        edit.putString("email", "");
        edit.apply();

        intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onLogoutFailure(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isAppRunning = false;
    }


}
