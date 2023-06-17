package com.example.chatapp.serialize;

import androidx.annotation.NonNull;

import com.example.chatapp.data.model.Token;

import org.json.JSONException;
import org.json.JSONObject;

public class TokenSerializer extends JSONSerializer<Token> {

    public TokenSerializer() {

    }

    @NonNull
    public JSONObject toJSON(@NonNull Token token) {
        JSONObject object = new JSONObject();

        try {
            object.put("token", token.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return object;
    }

    @NonNull
    public Token fromJSON(@NonNull JSONObject json) {
        try {
            String token = (String) json.get("token");
            return new Token(token);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
