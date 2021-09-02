package com.example.chatapp.util;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.chatapp.R;

public final class Logger {

    private static final String TAG_PREFIX = Constants.APP_NAME_SHORT + ".";

    public Logger() {}

    public static void e(@NonNull String feature, @NonNull String message) {
        Log.e(TAG_PREFIX + feature, message);
    }

    public static void d(@NonNull String feature, @NonNull String message) {
        Log.d(TAG_PREFIX + feature, message);
    }

    public static void i(@NonNull String feature, @NonNull String message) {
        Log.i(TAG_PREFIX + feature, message);
    }
}
