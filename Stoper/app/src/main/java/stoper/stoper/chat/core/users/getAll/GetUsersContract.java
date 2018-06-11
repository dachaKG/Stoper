package stoper.stoper.chat.core.users.getAll;

import java.util.List;

import stoper.stoper.chat.model.ChatUser;

public interface GetUsersContract {
    interface View {
        void onGetAllUsersSuccess(List<ChatUser> users);

        void onGetAllUsersFailure(String message);

        void onGetChatUsersSuccess(List<ChatUser> users);

        void onGetChatUsersFailure(String message);


    }

    interface Presenter {
        void getAllUsers();

        void getChatUsers();
    }

    interface Interactor {
        void getAllUsersFromFirebase();

        void getChatUsersFromFirebase();

    }


    interface OnGetAllUsersListener {
        void onGetAllUsersSuccess(List<ChatUser> users);

        void onGetAllUsersFailure(String message);
    }

    interface OnGetChatUsersListener {
        void onGetChatUsersSuccess(List<ChatUser> users);

        void onGetChatUsersFailure(String message);
    }


}

