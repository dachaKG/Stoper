package stoper.stoper.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import stoper.stoper.R;
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

    public RidesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.recycle_view_ride, container, false);
        rideList = new ArrayList<Ride>();
        Ride r1=new Ride();
        r1.setEndDestination("Paragovo");
        r1.setStartDestination("Fojnica");
        r1.setPrice(345);
        Ride r2=new Ride();
        r2.setEndDestination("Pariz");
        r2.setStartDestination("Nica");
        r2.setPrice(444);

        rideList.add(r1);
        rideList.add(r2);

        bundle = getArguments();
        if(bundle.getParcelableArrayList("ridesList") == null)
            bundle.putParcelableArrayList("ridesList", rideList);


        RideFragmentAdapter rideFragmentAdapter = new RideFragmentAdapter(bundle.<Ride>getParcelableArrayList("ridesList"));
        rideFragmentAdapter.setListener(new RideFragmentAdapter.Listener() {
            @Override
            public void onClick(int position) {
                bundle.putParcelable("selectedRide", bundle.<Ride>getParcelableArrayList("ridesList").get(position));
                fragment = new RideDetailsFragment();
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.main_screen, fragment);

                ft.addToBackStack(null);
                ft.commit();
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(rideFragmentAdapter);
        return  recyclerView;
    }
    /*@Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /*Ride r1=new Ride();
        r1.setEndDestination("Paragovo");
        r1.setStartDestination("Fojnica");
        r1.setPrice(345);
        Ride r2=new Ride();
        r2.setEndDestination("Pariz");
        r2.setStartDestination("Nica");
        r2.setPrice(444);

        rideList.add(r1);
        rideList.add(r2);//

        bundle = getArguments();

        rideList = bundle.getParcelableArrayList("rideList");
        mylistview = (ListView) view.findViewById(R.id.ridesList);

        CustomAdapter adapter = new CustomAdapter(getContext(), rideList);
        mylistview.setAdapter(adapter);
        mylistview.setOnItemClickListener(this);

    }*/

    /*@Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        String member_name = rideList.get(position).getStartDestination();
        Toast.makeText(getContext(), "" + member_name,
                Toast.LENGTH_SHORT).show();
    }*/
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        /*if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }*/
    }
/*
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
       *if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }*/

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     *//*
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
}
