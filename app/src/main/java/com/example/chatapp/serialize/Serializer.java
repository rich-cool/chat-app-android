package com.example.chatapp.serialize;

import org.json.JSONObject;

/**
 * Methods for handling <T> and JSON bi-directional relationship
 * @param <T> type to be serialized
 */
public interface Serializer<T> {
    JSONObject toJSON(T data);

    T fromJSON(JSONObject json);
}
