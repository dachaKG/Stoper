package stoper.stoper.fragments;


import android.content.AsyncTaskLoader;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import stoper.stoper.Api;
import stoper.stoper.R;
import stoper.stoper.activities.NavigationActivity;
import stoper.stoper.model.Ride;

import static java.lang.System.out;

/**
 * A simple {@link Fragment} subclass.
 */
public class OfferPriceFragment extends Fragment {

    Bundle bundle;
    Button finishButton;
    EditText priceOffer;

    private Button nextButton;
    private Fragment fragment;

    public OfferPriceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_offer_price, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        bundle = getArguments();
        priceOffer = view.findViewById(R.id.price_offer);
        nextButton = view.findViewById(R.id.button_offer_price_id);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(priceOffer.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Morate uneti cenu", Toast.LENGTH_SHORT).show();
                    return;
                }
                fragment = new OfferNoteFragment();
                bundle.putInt("priceOffer", Integer.valueOf(priceOffer.getText().toString()));
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.main_screen, fragment);

                ft.addToBackStack(null);
                ft.commit();






            }
        });

    }



}
