package stoper.stoper.fragments;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;


import stoper.stoper.R;
import stoper.stoper.model.LoginReq;
import stoper.stoper.model.RegistrationReq;
import stoper.stoper.model.User;


import static android.content.Context.MODE_PRIVATE;
import static java.lang.System.out;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link //StarterFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StarterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StarterFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static  EditText usernameText = null;
    private static  EditText  passwordText = null;
    private static TextView counterText=null;


    private static  String usernameArg = " ";
    private static  String  passwordArg = " ";

    private Button registerButton;
    private Button loginButton;
    int counter = 3;
    Bundle bundle;

    // TODO: Rename and change types of parameters


    //private OnFragmentInteractionListener mListener;

    public StarterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StarterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StarterFragment newInstance(String param1, String param2) {
        StarterFragment fragment = new StarterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_starter, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //bundle = getArguments();

        usernameText= view.findViewById(R.id.usernameID);
        passwordText= view.findViewById(R.id.passwordID);
        counterText = view.findViewById(R.id.counterID);
        counterText.setVisibility(View.GONE);

        getActivity().setTitle(R.string.find_ride);
        registerButton = view.findViewById(R.id.register);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment f = new RegistrationFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.main_screen, f);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        loginButton = view.findViewById(R.id.login);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginReq user = new LoginReq();
                usernameArg=usernameText.getText().toString().trim();
                passwordArg=passwordText.getText().toString().trim();


                try {

                    user.setEmail(usernameArg);
                    user.setPassword(passwordArg);
                    Gson gson = new Gson();
                    String json = gson.toJson(user);
                    System.out.println(json);
                    new HttpReqTask().execute(user);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
      
    }

    private class HttpReqTask extends AsyncTask<LoginReq, Void, RegistrationReq> {


        @Override
        protected RegistrationReq doInBackground(LoginReq... users) {
            try {
                String apiUrl = "http://192.168.0.11:8080/user/login";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                HttpEntity<LoginReq> user = new HttpEntity<>(users[0]);
                ResponseEntity<RegistrationReq> userTest = restTemplate.exchange(apiUrl, HttpMethod.POST,  user, RegistrationReq.class);
                return userTest.getBody();
            } catch (Exception ex) {
                Log.e("..", ex.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(RegistrationReq userLoged) {
            super.onPostExecute(userLoged);


            String baseName="detailsUSER";
            SharedPreferences loggedUserDetails;
            loggedUserDetails = getContext().getSharedPreferences(baseName, MODE_PRIVATE);

            SharedPreferences.Editor edit = loggedUserDetails.edit();
            edit.putString("firstName", userLoged.getFirst_name());
            edit.putString("lastname", userLoged.getLastName());
			edit.putString("email", userLoged.getEmail());
			edit.putInt("gender", userLoged.getGender());
            
			edit.apply();

            loggedUserDetails = getContext().getSharedPreferences(baseName, MODE_PRIVATE);

            String userName = loggedUserDetails.getString("firstName", "");
            String password = loggedUserDetails.getString("lastname", "");

            System.out.println(userName);
            System.out.println(password);
        }
    }
/*
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
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
    }
*/
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
