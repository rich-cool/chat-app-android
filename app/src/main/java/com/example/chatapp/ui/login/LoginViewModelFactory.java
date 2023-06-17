package com.example.chatapp.ui.login;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.chatapp.LoginDataSource;
import com.example.chatapp.LoginRepository;

/**
 * Factory for {@link LoginViewModel}
 *
 */
public class LoginViewModelFactory implements ViewModelProvider.Factory {

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(new LoginRepository());
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
