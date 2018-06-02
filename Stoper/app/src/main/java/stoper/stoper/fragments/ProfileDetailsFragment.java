package stoper.stoper.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import stoper.stoper.R;
import stoper.stoper.activities.CarActivity;
import stoper.stoper.activities.EditProfileActivity;
import stoper.stoper.activities.PhoneNumberActivity;
import stoper.stoper.activities.UserCustomSettingsActivity;
import stoper.stoper.model.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileDetailsFragment extends Fragment implements  View.OnClickListener {


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
        if(user.isConfirmed()){
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
        }

        if(intent != null){
            startActivity(intent);
        }
    }
}
