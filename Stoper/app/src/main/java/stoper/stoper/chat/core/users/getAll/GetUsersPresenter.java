package stoper.stoper.chat.core.users.getAll;

import java.util.List;

import stoper.stoper.chat.model.ChatUser;

public class GetUsersPresenter implements GetUsersContract.Presenter, GetUsersContract.OnGetChatUsersListener{
    private GetUsersContract.View mView;
    private GetUsersInteractor mGetUsersInteractor;

    public GetUsersPresenter(GetUsersContract.View view) {
        this.mView = view;
        mGetUsersInteractor = new GetUsersInteractor(this);
    }
    //GetUsersContract.OnGetAllUsersListener

    @Override
    public void getAllUsers() {
        mGetUsersInteractor.getAllUsersFromFirebase();
    }

    @Override
    public void getChatUsers() {
        mGetUsersInteractor.getChatUsersFromFirebase();
    }

    /*@Override
    public void onGetAllUsersSuccess(List<ChatUser> users) {
        mView.onGetAllUsersSuccess(users);
    }

    @Override
    public void onGetAllUsersFailure(String message) {
        mView.onGetAllUsersFailure(message);
    }*/

    @Override
    public void onGetChatUsersSuccess(List<ChatUser> users) {
        mView.onGetChatUsersSuccess(users);
    }

    @Override
    public void onGetChatUsersFailure(String message) {
        mView.onGetChatUsersFailure(message);
    }
}
