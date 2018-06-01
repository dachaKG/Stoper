package stoper.stoper.util;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import stoper.stoper.model.User;

public class MockData extends Application {
    public static List<User> users;
    public static List<User> UsersDatabase(){
        if(users == null) {
           users = new ArrayList<User>();
            User user = new User(0, "Danilo", "Acimovic", 1994, "danilo.a@yahoo.com", "123",
                    "Dipl ing, elektroyehnike. Zaposle", "24554745", true, "pocetnik","+381");
            user.setId((long) 0);
            users.add(user);
            User user1 = new User(0, "Danilo", "Dimitrijevic", 1994, "danilo.dim@yahoo.com", "123",
                    "Dipl ing, elektroyehnike. Zaposle", "24554745", true, "pocetnik","+381");
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
