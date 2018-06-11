package stoper.stoper.chat.core.users.getAll;

import android.text.TextUtils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.RemoteMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import stoper.stoper.chat.model.Chat;
import stoper.stoper.chat.model.ChatUser;
import stoper.stoper.chat.utils.Constants;

public class GetUsersInteractor implements GetUsersContract.Interactor {
    private static final String TAG = "GetUsersInteractor";

    private GetUsersContract.OnGetAllUsersListener mOnGetAllUsersListener;
    private GetUsersContract.OnGetChatUsersListener onGetChatUsersListener;

    List<ChatUser> chatUsers = new ArrayList<>();

    /*public GetUsersInteractor(GetUsersContract.OnGetAllUsersListener onGetAllUsersListener) {
        this.mOnGetAllUsersListener = onGetAllUsersListener;
    }*/

    public GetUsersInteractor(GetUsersContract.OnGetChatUsersListener onGetChatUsersListener) {
        this.onGetChatUsersListener = onGetChatUsersListener;
    }

    @Override
    public void getAllUsersFromFirebase() {
        FirebaseDatabase.getInstance().getReference().child(Constants.ARG_USERS).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> dataSnapshots = dataSnapshot.getChildren().iterator();
                List<ChatUser> users = new ArrayList<>();
                while (dataSnapshots.hasNext()) {
                    DataSnapshot dataSnapshotChild = dataSnapshots.next();

                    ChatUser user = dataSnapshotChild.getValue(ChatUser.class);
                    if (!TextUtils.equals(user.uid, FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                        users.add(user);
                        chatUsers.add(user);
                    }
                }

                mOnGetAllUsersListener.onGetAllUsersSuccess(users);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                mOnGetAllUsersListener.onGetAllUsersFailure(databaseError.getMessage());
            }
        });
    }

    public List<ChatUser> getAllUsers() {
        final List<ChatUser> userList = new ArrayList<>();
        FirebaseDatabase.getInstance().getReference().child(Constants.ARG_USERS).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> dataSnapshots = dataSnapshot.getChildren().iterator();
                List<ChatUser> users = new ArrayList<>();
                while (dataSnapshots.hasNext()) {
                    DataSnapshot dataSnapshotChild = dataSnapshots.next();

                    ChatUser user = dataSnapshotChild.getValue(ChatUser.class);
                    if (!TextUtils.equals(user.uid, FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                        users.add(user);
                    }
                }

                userList.addAll(users);
                //mOnGetAllUsersListener.onGetAllUsersSuccess(users);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                mOnGetAllUsersListener.onGetAllUsersFailure(databaseError.getMessage());
            }
        });
        return userList;
    }

    @Override
    public void getChatUsersFromFirebase() {
        FirebaseDatabase.getInstance().getReference().child(Constants.ARG_CHAT_USERS).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> dataSnapshots = dataSnapshot.getChildren().iterator();
                List<ChatUser> users = new ArrayList<>();
                while (dataSnapshots.hasNext()) {
                    DataSnapshot dataSnapshotChild = dataSnapshots.next();
                    //ChatUser user1 = dataSnapshot.getValue(
                    //Chat chat = dataSnapshotChild.getValue(Chat.class);
                    // users = getAllUsers();
                    getAllUsersFromFirebase();
                    System.out.println(chatUsers.size());
                    HashMap<Object, HashMap<Object, Object>> mapa = (HashMap<Object, HashMap<Object, Object>>) dataSnapshotChild.getValue();
                    if (mapa != null) {
                        for (HashMap<Object, Object> firstMap : mapa.values()) {
                            Chat chat = new Chat();
                            chat.setTimestamp((Long) firstMap.get("timestamp"));
                            chat.setMessage((String) firstMap.get("message"));
                            chat.setReceiver((String) firstMap.get("receiver"));
                            chat.setReceiverUid((String) firstMap.get("receiverUid"));
                            chat.setSender((String) firstMap.get("sender"));
                            chat.setSenderUid((String) firstMap.get("senderUid"));
                            if (TextUtils.equals(chat.getSenderUid(), FirebaseAuth.getInstance().getCurrentUser().getUid())) {

                            }
                        }
                    }
                   /* if (!TextUtils.equals(chat., FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                        users.add(user);
                    }*/
                }
                onGetChatUsersListener.onGetChatUsersSuccess(users);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                onGetChatUsersListener.onGetChatUsersFailure(databaseError.getMessage());
            }
        });
    }


}

