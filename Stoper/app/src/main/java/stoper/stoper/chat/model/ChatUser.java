package stoper.stoper.chat.model;

public class ChatUser {
    public String uid;
    public String email;
    public String firebaseToken;

    public ChatUser() {

    }

    public ChatUser(String uid, String email, String firebaseToken) {
        this.uid = uid;
        this.email = email;
        this.firebaseToken = firebaseToken;
    }

    public ChatUser(String uid, String email) {
        this.uid = uid;
        this.email = email;
    }
}
