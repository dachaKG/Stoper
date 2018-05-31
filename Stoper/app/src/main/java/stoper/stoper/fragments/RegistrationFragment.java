package stoper.stoper.fragments;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import stoper.stoper.R;
import stoper.stoper.model.RegistrationReq;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link /*RegistrationFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegistrationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistrationFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private Button submitRegButton;
    // TODO: Rename and change types of parameters
    private Button register_with_mail_button;
    private LinearLayout ll;
    private EditText genderEditText;
    private EditText birthYearEditText;
    private EditText nameEditText;
    private EditText lastnameEditText;
    private EditText passwordEditText;
    private EditText repeatPaswordEditText;
    private EditText mailEditText;
    //private OnFragmentInteractionListener mListener;

    private static  String nameArg = " ";
    private static  String  lastnameArg = " ";
    private static  int genderArg = 0;
    private static  String  emailArg = " ";
    private static  int yearOfBirthArg = 0;
    private static  String  passwordArg = " ";
    private static  String usernameArg = " ";


    public RegistrationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegistrationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegistrationFragment newInstance(String param1, String param2) {
        RegistrationFragment fragment = new RegistrationFragment();
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
        return inflater.inflate(R.layout.fragment_registration, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle(R.string.register);
        /**
         * Expand registration form via mail
         */
        register_with_mail_button = getView().findViewById(R.id.register_with_mail);
        register_with_mail_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext(), "okolo svuda je tama", Toast.LENGTH_LONG).show();
                if(ll.getVisibility()==View.GONE)
                    ll.setVisibility(View.VISIBLE);
                else{/*
                    if(checkFields()){
                        Toast.makeText(v.getContext(), "dobro je", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(v.getContext(), "Nije dobro", Toast.LENGTH_LONG).show();
                    }*/
                    
                }
            }
        });
        ll=view.findViewById(R.id.popup);
        ll.setVisibility(View.GONE);

        /**
         * Gender choice
         */

        genderEditText=view.findViewById(R.id.gender);
        genderEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence genders[] = new CharSequence[] {"Muski", "Zenski"};

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setItems(genders, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // the user clicked on colors[which]
                        genderEditText.setText(genders[which]);
                        TextInputLayout til = getView().findViewById(R.id.textInputLayout1);
                        til.setErrorEnabled(false);
                    }
                });
                builder.show();
            }
        });

        /**
         * Birth year choice
         */
        birthYearEditText=view.findViewById(R.id.birth_year);
        birthYearEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<String> years=new ArrayList<>();
                int maxYear=Calendar.getInstance().get(Calendar.YEAR);
                for(int i=maxYear-18;i>maxYear-90;i--){
                    years.add(Integer.toString(i));
                }

                final CharSequence[] charSequenceItems = years.toArray(new CharSequence[years.size()]);
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setItems(years.toArray(new CharSequence[years.size()]), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // the user clicked on colors[which]
                        birthYearEditText.setText(charSequenceItems[which]);
                        TextInputLayout til = getView().findViewById(R.id.textInputLayout4);
                        til.setErrorEnabled(false);
                    }
                });
                builder.show();
            }
        });


        nameEditText= view.findViewById(R.id.name);
		lastnameEditText= view.findViewById(R.id.lastname);
		mailEditText= view.findViewById(R.id.e_mail);
		passwordEditText= view.findViewById(R.id.password);
		birthYearEditText= view.findViewById(R.id.birth_year);
		genderEditText= view.findViewById(R.id.gender);
		
        submitRegButton = view.findViewById(R.id.submitReg);
        submitRegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegistrationReq user = new RegistrationReq();
                checkFields();
				nameArg=nameEditText.getText().toString().trim();
				lastnameArg=lastnameEditText.getText().toString().trim();
				emailArg=mailEditText.getText().toString().trim();
				passwordArg=passwordEditText.getText().toString().trim();
                yearOfBirthArg=Integer.valueOf(birthYearEditText.getText().toString());
                if((genderEditText.getText().toString()).equals("Muski")){
                    genderArg=1;
                }else
                {
                    genderArg=2;
                }

                try {

                    user.setEmail(usernameArg);
                    user.setPassword(passwordArg);
                    user.setFirstName(nameArg);
                    user.setLastName(lastnameArg);
                    user.setGender(genderArg);
                    user.setPassword(passwordArg);
                    Gson gson = new Gson();
                    String json = gson.toJson(user);
                    System.out.println(json);
                    //new StarterFragment.HttpReqTask().execute(user);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        //mAnimatosr.start();
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
    private boolean checkFields(){
        nameEditText= getView().findViewById(R.id.name);
        lastnameEditText=getView().findViewById(R.id.lastname);
        mailEditText=getView().findViewById(R.id.e_mail);
        passwordEditText=getView().findViewById(R.id.password);
        repeatPaswordEditText=getView().findViewById(R.id.repeat_password);
        TextInputLayout til2 = getView().findViewById(R.id.textInputLayout2);
        TextInputLayout til3 = getView().findViewById(R.id.textInputLayout3);
        TextInputLayout til5 = getView().findViewById(R.id.textInputLayout5);
        TextInputLayout til6 = getView().findViewById(R.id.textInputLayout6);
        TextInputLayout til7 = getView().findViewById(R.id.textInputLayout7);
        if(genderEditText.getText().length()==0){
            TextInputLayout til1 = getView().findViewById(R.id.textInputLayout1);
            til1.setError("Ovo polje je obavezno");
            return false;
        }else if(nameEditText.getText().length()==0){

            til2.setError("Ovo polje je obavezno");
            return false;
        }else {
            til2.setErrorEnabled(false);
        }
        if(lastnameEditText.getText().length()==0){
            til3.setError("Ovo polje je obavezno");
            return false;
        }else {
            til3.setErrorEnabled(false);
        }
        if(birthYearEditText.getText().length()==0){
            TextInputLayout til = (TextInputLayout) getView().findViewById(R.id.textInputLayout4);
            til.setError("Ovo polje je obavezno");
            return false;
        }else if(!isEmailValid(mailEditText.getText().toString())){
            til5.setError("Molimo Vas unesite vazecu e-mail adresu");
            return false;
        }else {
            til5.setErrorEnabled(false);
        }
        if(passwordEditText.getText().length()<8){
            til6.setError("Izaberite lozinku od najmanje 8 znakova");
            return false;
        }else {
            til6.setErrorEnabled(false);
        }
        if(!(repeatPaswordEditText.getText().toString().equals(passwordEditText.getText().toString()))){
            til7.setError("Lozinke se moraju podudarati");
            return false;
        }else{
            til7.setErrorEnabled(false);
        }

        return true;
    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
