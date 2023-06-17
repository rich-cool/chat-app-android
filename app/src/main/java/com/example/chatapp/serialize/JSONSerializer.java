package com.example.chatapp.serialize;

import org.json.JSONObject;

/**
 * Methods for handling <T> and JSON bi-directional relationship
 * @param <T> type to be serialized
 */
public abstract class JSONSerializer<T> {

    public abstract JSONObject toJSON(T data);
    public abstract T fromJSON(JSONObject json);
}