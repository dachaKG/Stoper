package stoper.stoper;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import stoper.stoper.fragments.MainFragment;
import stoper.stoper.fragments.SearchFragment;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    private MainFragment firstFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = findViewById(R.id.drawer_layout);

        /*if (findViewById(R.id.linear_layout_main) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create a new Fragment to be placed in the activity layout
            firstFragment = new MainFragment();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            firstFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.linear_layout_main, firstFragment).commit();

        }*/


       /* NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                Fragment fragment = null;

                if(id == R.id.navigation_item_search){
                    //invalidateOptionsMenu();
                    fragment = new SearchFragment();
                    Toast.makeText(MainActivity.this, menuItem.getTitle(), Toast.LENGTH_LONG).show();
                } else if (id == R.id.navigation_item_rides){
                    invalidateOptionsMenu();
                    fragment = new MainFragment();
                    Toast.makeText(MainActivity.this, menuItem.getTitle(), Toast.LENGTH_LONG).show();

                }

                if(fragment != null){

                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    ft.replace(R.id.fragment_main, fragment);

                    ft.addToBackStack(null);
                    ft.commit();
                }

                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();

                *//*mDrawerLayout = (DrawerLayout) findViewById(R.id.main_fragment);
                mDrawerLayout.findViewById(R.id.main_fragment);
                mDrawerLayout.closeDrawer(GravityCompat.END);
*//*
                return true;
            }
        });*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.END);
                return true;
            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
