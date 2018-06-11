package stoper.stoper.fragments;


import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import stoper.stoper.Api;
import stoper.stoper.R;
import stoper.stoper.model.User;
import stoper.stoper.util.MockData;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private int activeTab = 0;
    private User user = null;
    SharedPreferences loggedUserDetails;
    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //user = MockData.UsersDatabase().get(0);
        loggedUserDetails = getActivity().getApplicationContext().getSharedPreferences(Api.baseName, MODE_PRIVATE);
        user =  new Gson().fromJson(loggedUserDetails.getString("userJson", ""), User.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        SectionPagerAdapter sectionPagerAdapter = new SectionPagerAdapter(getChildFragmentManager());

        TextView head_name = (TextView) view.findViewById(R.id.profile_head_name);
        head_name.setText(user.getFirstName());
        TextView head_level = (TextView) view.findViewById(R.id.profile_head_level);
        head_level.setText(user.getLevel());
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.pager);
        viewPager.setAdapter(sectionPagerAdapter);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        if(user.getProfileImage() != null && user.getProfileImage().length > 0) {
            ImageView imageView = (ImageView) view.findViewById(R.id.profile_image);
            Bitmap bitmap = BitmapFactory.decodeByteArray(user.getProfileImage(), 0, user.getProfileImage().length);

            imageView.setImageBitmap(bitmap);
        }
        return view;
    }


    /*@Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("activeTab", activeTab);

    }*/

    private class SectionPagerAdapter extends FragmentPagerAdapter {
        public SectionPagerAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public Fragment getItem(int position) {
            activeTab = position;
            switch (position){
                case 0:
                    return new ProfileDetailsFragment();
                case 1:
                    return new ProfileAccountFragment();
            }

            return  null;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getResources().getText(R.string.profile_details_tab);
                case 1:
                    return getResources().getText(R.string.profile_account_tab);
            }
            return null;
        }
    }

    public User getUser(){
        return this.user;
    }
}
