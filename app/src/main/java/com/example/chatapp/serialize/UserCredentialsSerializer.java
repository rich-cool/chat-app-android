package com.example.chatapp.serialize;

import androidx.annotation.NonNull;

import com.example.chatapp.data.model.UserCredentials;

import org.json.JSONException;
import org.json.JSONObject;

public class UserCredentialsSerializer implements Serializer<UserCredentials> {

    public UserCredentialsSerializer() {}

    @NonNull
    public JSONObject toJSON(@NonNull UserCredentials credentials) {
        JSONObject object = new JSONObject();

        try {
            // Backend only accepts email for now
            object.put("email", credentials.getUsername());
            object.put("password", credentials.getPassword());
            return object;
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @NonNull
    public UserCredentials fromJSON(JSONObject json) {
        try {
            String username = (String) json.get("email");
            String password = (String) json.get("password");
            return new UserCredentials(username, password);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
