package com.example.chatapp.ui.login;

import android.os.Handler;
import android.os.HandlerThread;
import android.util.Patterns;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.chatapp.data.model.UserCredentials;
import com.example.chatapp.data.model.response.Result;
import com.example.chatapp.LoginRepository;
import com.example.chatapp.util.Constants;

public class LoginViewModel extends ViewModel {

    private static final String TAG = Constants.LOGIN_FEATURE;

    private LoginRepository repo;
    private static MutableLiveData<Result> loginResult = new MutableLiveData<>();

    private Handler loginHandler;
    private HandlerThread thread = new HandlerThread("HandlerThread");

    public LoginViewModel(LoginRepository repo) {
        this.repo = repo;
        thread.start();
        loginHandler = new Handler(thread.getLooper());
    }

    public void login(@NonNull String username, @NonNull String password) {
        UserCredentials credentials = new UserCredentials(username, password);
        Runnable loginRunnable = new Runnable() {
                @Override
                public void run() {
                    loginResult.postValue(repo.login(credentials));
                }
        };
        loginHandler.postDelayed(loginRunnable, 1000);
    }

    public LiveData<Result> getLoginResult() {
        return loginResult;
    }

    @NonNull
    public boolean usernameIsValid(@Nullable String username) {
        if (username == null)
            return false;
        if (!(username.contains("@"))) {
            return false;
        }

        return Patterns.EMAIL_ADDRESS.matcher(username).matches();
    }

    @NonNull
    public boolean passwordIsValid(@Nullable String password) {
        return password != null && password.trim().length() > 5;
    }

    public boolean credentialsIsValid(@Nullable String username, @Nullable String password) {
        return usernameIsValid(username) && passwordIsValid(password);
    }
}
