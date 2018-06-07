package stoper.stoper.util;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.google.gson.Gson;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import stoper.stoper.Api;
import stoper.stoper.DTO.UserCustomSettingsDTO;
import stoper.stoper.R;
import stoper.stoper.model.Ride;
import stoper.stoper.model.User;

import static java.lang.System.out;

public class MockData extends Application {
    public static List<User> users;
    public static List<User> UsersDatabase(){
        if(users == null) {
           users = new ArrayList<User>();
            User user = new User(0, "Danilo", "Acimovic", 1994, "danilo.a@yahoo.com", "123",
                    "Dipl ing, elektroyehnike. Zaposle","123433", "+381", true,"pocetnik",
                    1, 1, 1,2,
                    0,1,1,"lo564ai","Peugeot", "307", 2004);
            user.setId((long) 0);
            users.add(user);
            User user1 = new User(0, "Danilo", "Dimitrijevic", 1994, "danilo.dim@yahoo.com", "123",
                    "Dipl ing, elektroyehnike. Zaposle", "24554745", "+381", true,"pocetnik",
                    0, 1, 1,2,
                    0,1,1,"kg564ai","Peugeot", "307", 2004);
            user.setId((long) 1);
            users.add(user1);
            User user2 = new User(0, "Nikola", "Smiljanic", 1994, "nikola@gmail.com", "123",
                    "Dipl ing, elektroyehnike. Zaposle", "24554745", true, "pocetnik","+381");
            user.setId((long) 2);
            users.add(user2);
            User user3 = new User(0, "Danilo", "Acimovic", 1994, "danilo.a@yahoo.com", "123",
                    "Dipl ing, elektroyehnike. Zaposle", "24554745", true, "pocetnik","+381");
            user.setId((long) 3);
            users.add(user3);
            User user4 = new User(0, "Luka", "Sicar", 1994, "luka.a@yahoo.com", "123",
                    "Dipl ing, elektroyehnike. Zaposle", "24554745", true, "pocetnik","+381");
            user.setId((long) 4);
            users.add(user4);
        }
        return  users;
    }

}

