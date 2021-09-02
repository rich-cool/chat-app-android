package com.example.chatapp.ui.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.chatapp.R;
import com.example.chatapp.settings.SettingsManager;
import com.example.chatapp.ui.MainActivity;
import com.example.chatapp.ui.login.LoginFragment;
import com.example.chatapp.util.Logger;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, LoginFragment.newInstance())
                .commit();
    }
}