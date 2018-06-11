package stoper.stoper.chat.core.logout;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;

import org.springframework.http.HttpEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import stoper.stoper.Api;
import stoper.stoper.activities.LoginActivity;
import stoper.stoper.activities.NavigationActivity;
import stoper.stoper.model.LoginReq;

public class LogoutInteractor implements LogoutContract.Interactor {
    private LogoutContract.OnLogoutListener mOnLogoutListener;

    public LogoutInteractor(LogoutContract.OnLogoutListener onLogoutListener) {
        mOnLogoutListener = onLogoutListener;
    }

    @Override
    public void performFirebaseLogout() {
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {

            FirebaseAuth.getInstance().signOut();
            mOnLogoutListener.onSuccess("Successfully logged out!");

        } else {
            mOnLogoutListener.onFailure("No user logged in yet!");
        }
    }


}