package com.example.chatapp.data.model;

import android.util.Pair;

import com.example.chatapp.data.model.response.ResponseCode;
import com.example.chatapp.data.model.response.ServiceResponse;
import com.example.chatapp.serialize.TokenSerializer;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ServiceResponseTest {
    @Test
    public void test() throws JSONException {
        String expected = "9e08528c6d1dd0286a2c9c2e8f9080598c1785b1";
        String json = "{\"token\":\"" + expected + "\"}";
        JSONObject jsonObject = new JSONObject(json);

        ServiceResponse response = new ServiceResponse(new Pair<>(new ResponseCode(200), new JSONObject(json)));

        Token token = response.getData(new TokenSerializer());
        String actual = token.toString();

        //assertEquals(expected, actual);
    }
}
