package stoper.stoper.fragments;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;

import stoper.stoper.Api;
import stoper.stoper.R;
import stoper.stoper.activities.RideDetailsActivity;
import stoper.stoper.adapter.RideFragmentAdapter;
import stoper.stoper.model.Ride;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link// RidesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class RidesFragment extends Fragment {

    ArrayList<Ride> rideList;
    private Bundle bundle;
    private Fragment fragment;
    RecyclerView recyclerView;

    public RidesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        recyclerView = (RecyclerView) inflater.inflate(R.layout.recycle_view_ride, container, false);
        rideList = new ArrayList<Ride>();

        new HttpReqTask().execute();

        return recyclerView;
    }

    private class HttpReqTask extends AsyncTask<Void, Void, Ride[]> {


        @Override
        protected Ride[] doInBackground(Void... voids) {
            try {
                String apiUrl = Api.apiUrl + "/ride";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                Ride[] getRides = (Ride[]) restTemplate.getForObject(apiUrl, Ride[].class);
                return getRides;
            } catch (Exception ex) {
                Log.e("", ex.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(Ride[] rides) {
            super.onPostExecute(rides);
            bundle = getArguments();
            if (bundle.getParcelableArrayList("ridesList") == null)
                bundle.putParcelableArrayList("ridesList", new ArrayList<>(Arrays.asList(rides)));

            RideFragmentAdapter rideFragmentAdapter = new RideFragmentAdapter(bundle.<Ride>getParcelableArrayList("ridesList"));
            rideFragmentAdapter.setListener(new RideFragmentAdapter.Listener() {
                @Override
                public void onClick(int position) {
                    Ride selectedRide = bundle.<Ride>getParcelableArrayList("ridesList").get(position);
                    bundle.putSerializable("serRide", selectedRide);
                    Intent intent = new Intent(getActivity(), RideDetailsActivity.class);
                    intent.putExtra("selectedRide", (new Gson()).toJson(selectedRide));
                    startActivity(intent);

                }
            });

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(rideFragmentAdapter);

        }


    }

}
