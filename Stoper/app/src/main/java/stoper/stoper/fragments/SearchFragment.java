package stoper.stoper.fragments;


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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import stoper.stoper.R;
import stoper.stoper.activities.NavigationActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    private Button searchButton;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

/*
        profilePicture = (ImageView) view.findViewById(R.id.profilePicture);
        profileName = (TextView) view.findViewById(R.id.profileName);
        profileName.setEnabled(false);
        QRcodeView = (ImageView) view.findViewById(R.id.qr_code);
        profileBirthday = (EditText) view.findViewById(R.id.profileBirthday);
        profileBirthday.setEnabled(false);
*/
//        profileBirthday.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//
//            }
//        });
/*
        Log.i("userID",UserPreference.readLoggedInUser(getActivity()));

        profileName.setText(loggedInUser.getName());

        if(loggedInUser.getDate().equals("00-00-0000")) {
            profileBirthday.setText("");
            profileName.setGravity(Gravity.CENTER);

        } else {
            profileBirthday.setText(loggedInUser.getDate());
        }


        new DownloadImageTask(profilePicture)
                .execute(loggedInUser.getImageURL());

        // generisanje QR coda
        QRCodeWriter writer = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = writer.encode(userID, BarcodeFormat.QR_CODE, 512, 512);
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }
            QRcodeView.setImageBitmap(bmp);

        } catch (WriterException e) {
            e.printStackTrace();
        }
*/
        getActivity().setTitle(R.string.find_ride);

        Bundle b = getArguments();
        EditText et1 = view.findViewById(R.id.start_destination);
        if(b!=null) {
            String startDestination = b.getString("startDestination");
            if (startDestination != null) {
                et1.setText(startDestination);
            }
        }
        et1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment f = new DestinationFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.main_screen, f);

                //ft.addToBackStack(null);
                ft.commit();
                //getActivity().getSupportFragmentManager().executePendingTransactions();
            }

        });

    }

}
