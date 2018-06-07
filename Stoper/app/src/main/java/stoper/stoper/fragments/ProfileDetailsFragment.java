package stoper.stoper.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import stoper.stoper.R;
import stoper.stoper.activities.CarActivity;
import stoper.stoper.activities.EditProfileActivity;
import stoper.stoper.activities.PhoneNumberActivity;
import stoper.stoper.activities.ShowProfileActivity;
import stoper.stoper.activities.UserCustomSettingsActivity;
import stoper.stoper.model.User;
import stoper.stoper.util.MockData;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileDetailsFragment extends Fragment implements  View.OnClickListener {

    MockData mockData;

    public ProfileDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_details, container, false);

        ImageView imageView = (ImageView)view.findViewById(R.id.submitted_image);
        ProfileFragment fragment = (ProfileFragment) getParentFragment();
        User user = fragment.getUser();
        if(user.getConfirmed()){
            imageView.setImageResource(R.drawable.baseline_check_circle_outline_24);
        }else{
            imageView.setImageResource(R.drawable.baseline_highlight_off_24);
        }

        TextView biography_link = (TextView)view.findViewById(R.id.link_to_biography_edit);
        biography_link.setOnClickListener(this);

        TextView settings_link = (TextView)view.findViewById(R.id.link_to_settings_edit);
        settings_link.setOnClickListener(this);

        TextView phone_link = (TextView) view.findViewById(R.id.link_to_phone_number_edit);
        phone_link.setOnClickListener(this);

        TextView car_link = (TextView) view.findViewById(R.id.link_to_car_edit);
        car_link.setOnClickListener(this);

        TextView show_profile_link = (TextView) view.findViewById(R.id.link_to_show_profil);
        show_profile_link.setOnClickListener(this);

        if(user.getCarBrand() != null && !user.getCarBrand().equals("")) {
            TextView car_brand_model = (TextView) view.findViewById(R.id.profile_details_car_brand);
            car_brand_model.setText(String.format("%s %s", user.getCarBrand(), user.getCarBrandModel()));
            car_brand_model.setVisibility(View.VISIBLE);

            TextView color = (TextView) view.findViewById(R.id.profile_details_car_color);
            List<String> colors = Arrays.asList(getResources().getStringArray(R.array.colors));
            String color_value = colors.get(user.getCarColor());
            color.setText(color_value);
            color.setVisibility(View.VISIBLE);
        }
        return view;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        Intent intent = null;
        switch (id){
            case(R.id.link_to_biography_edit):
                intent = new Intent(getActivity(), EditProfileActivity.class);
                intent.putExtra(EditProfileActivity.EXTRA_SCROLL_TO_ELEMENT, R.id.biography_text_view);
                break;
            case(R.id.link_to_settings_edit):
                intent = new Intent(getActivity(), UserCustomSettingsActivity.class);
                break;
            case(R.id.link_to_phone_number_edit):
                intent = new Intent(getActivity(), EditProfileActivity.class);
                intent.putExtra(EditProfileActivity.EXTRA_SCROLL_TO_ELEMENT, R.id.area_call_text_view);
                break;
            case(R.id.link_to_car_edit):
                intent = new Intent(getActivity(), CarActivity.class);
                break;
            case(R.id.link_to_show_profil):
                intent = new Intent(getActivity(),ShowProfileActivity.class);
                break;
        }

        if(intent != null){
            startActivity(intent);
        }
    }
}
