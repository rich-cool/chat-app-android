package com.example.chatapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.chatapp.R;
import com.example.chatapp.settings.SettingsManager;
import com.example.chatapp.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        /** Start {@link LoginActivity} if user not authenticated
         *
         */
        if (!(userIsAuthenticated())) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        // TODO: MainActivity
    }

    @NonNull
    private boolean userIsAuthenticated() {
        if (SettingsManager.getInstance().isAuthenticated(this)) {
            return true;
        }
        return false;
    }
}