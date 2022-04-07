package com.example.chatapp.service;

import android.util.Pair;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.chatapp.data.model.response.ServiceResponse;
import com.example.chatapp.data.model.response.ResponseCode;
import com.example.chatapp.util.Constants;
import com.example.chatapp.util.Logger;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Provides methods for communicating with api endpoints
 *
 */
public abstract class APIService {
    private static final String TAG = Constants.LOGIN_FEATURE;
    
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private Response httpResponse;

    private enum Method {
        POST,
        GET
    }

    @NonNull
    public abstract ServiceResponse login(@NonNull JSONObject json);

    @NonNull
    protected ServiceResponse get(@NonNull String url, @NonNull JSONObject json) {
        // TODO: OkHttp get
        return new ServiceResponse(new Pair<>(new ResponseCode(200), new JSONObject()));
    }

    @NonNull
    protected ServiceResponse post(@NonNull String url, @NonNull JSONObject json)
            throws IOException, InterruptedException, JSONException {
        return post(url, json, null);
    }

    @NonNull
    protected ServiceResponse post(
            @NonNull String url, @NonNull JSONObject json,
            @Nullable Map<String, String> header)
            throws IOException, InterruptedException, JSONException {
        postCall(url, json, header);

        if (httpResponse != null) {
            return new ServiceResponse(new Pair<>(new ResponseCode(httpResponse.code()),
                    new JSONObject(httpResponse.body().string())));
        }
        return new ServiceResponse(new Pair<>(new ResponseCode(-1000),
                new JSONObject()));
    }

    private void postCall(
            @NonNull String url, @NonNull JSONObject json,
            @Nullable Map<String, String> header)
            throws IOException, InterruptedException, JSONException {
        call(url, json, header, Method.POST);
    }

    private void getCall(
            @NonNull String url, @Nullable Map<String,
            String> header)
            throws IOException, InterruptedException, JSONException {
        call(url, null, header, Method.GET);
    }

    /**
     * Makes call to api endpoint
     * @param json optional argument used to populate request body
     * @param header optional headers
     */
    private void call(
            @NonNull String url, @Nullable JSONObject json,
            @Nullable Map<String, String> header, @NonNull Method method)
            throws IOException, InterruptedException, JSONException {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        Request.Builder builder = new Request.Builder();
        builder.url(url)
                .header("accept-language", Locale.getDefault().getLanguage());

        if (method == Method.POST) {
            RequestBody body = addBody(json);
            builder.post(body);
        }
        else if (method == Method.GET) {
            builder.get();
        }

        Request request = builder.build();

        CountDownLatch countDownLatch = new CountDownLatch(1);

        client.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        Logger.e(TAG, "Request Failed");
                        countDownLatch.countDown();
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        Logger.i(TAG, "Request Succeeded");
                        httpResponse = response;
                        countDownLatch.countDown();
                    }
                });

        countDownLatch.await();
    }

    /**
     * Adds json data to request body
     * @param json used to populate request body
     *
     */
    @NonNull
    private RequestBody addBody(@NonNull JSONObject json) throws JSONException {
        FormBody.Builder builder = new FormBody.Builder();

        Iterator<String> keys = json.keys();

        while (keys.hasNext()) {
            String key = keys.next();
            builder.add(key.toString(), (String) json.get(key));
        }
        RequestBody body = builder.build();
        return body;
    }
}
