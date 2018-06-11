package stoper.stoper.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import stoper.stoper.R;
import stoper.stoper.activities.ChangePasswordActivity;
import stoper.stoper.activities.RecievedRatesActivity;
import stoper.stoper.activities.StatisticDataActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileAccountFragment extends Fragment implements View.OnClickListener {


    public ProfileAccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_account, container, false);

        TextView statistic_data = (TextView) view.findViewById(R.id.account_link_to_statistic_data);
        statistic_data.setOnClickListener(this);

        TextView notifications_settings = (TextView) view.findViewById(R.id.account_link_to_notifications_settings);
        notifications_settings.setOnClickListener(this);

        TextView password_change = (TextView) view.findViewById(R.id.account_link_to_password_change);
        password_change.setOnClickListener(this);

        TextView help = (TextView) view.findViewById(R.id.account_link_to_help);
        help.setOnClickListener(this);

        TextView privacy = (TextView) view.findViewById(R.id.account_link_to_privacy);
        privacy.setOnClickListener(this);

        TextView terms_of_service = (TextView) view.findViewById(R.id.account_link_to_terms_of_service_title);
        terms_of_service.setOnClickListener(this);

        TextView recieved_rates = (TextView)view.findViewById(R.id.account_link_to_recieved_rates);
        recieved_rates.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        Intent intent = null;
        switch (id){
            case R.id.account_link_to_statistic_data:
                intent = new Intent(getActivity(), StatisticDataActivity.class);
                break;
            case R.id.account_link_to_notifications_settings:
                break;
            case R.id.account_link_to_password_change:
                intent = new Intent(getActivity(), ChangePasswordActivity.class);
                break;
            case R.id.account_link_to_help:
                break;
            case R.id.account_link_to_privacy:
                break;
            case R.id.account_link_to_terms_of_service_title:
                break;
            case R.id.account_link_to_recieved_rates:
                intent = new Intent(getActivity(), RecievedRatesActivity.class);
                break;
        }
        if (intent != null){
            startActivity(intent);
        }
    }
}
