package com.example.chatapp.settings;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.chatapp.data.model.Token;

public class SettingsManager {

    private static final Object lock = new Object();
    private static volatile SettingsManager instance;

    private SettingsManager() {

    }

    @NonNull
    public static SettingsManager getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new SettingsManager();
                }
            }
        }

        return instance;
    }

    @Nullable
    private SharedPreferences getPrefs(@Nullable Context context) {
        if (context != null) {
            return context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        }

        return null;
    }

    public void storeAuthToken(@NonNull Token token, @Nullable Context context) {
        SharedPreferences prefs = getPrefs(context);
        prefs.edit()
                .putString("token", token.toString())
                .apply();
    }

    @Nullable
    public Token getAuthToken(@Nullable Context context) {
        SharedPreferences prefs = getPrefs(context);
        String token = prefs.getString("token", null);

        if (token != null) {
            return new Token(token);
        }

        return null;
    }

    @NonNull
    public boolean isAuthenticated(@Nullable Context context) {
        SharedPreferences prefs = getPrefs(context);
        String token = prefs.getString("token", null);

        if (token != null) {
            return true;
        }

        return false;
    }
}
