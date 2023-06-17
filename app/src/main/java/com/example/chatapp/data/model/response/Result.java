package com.example.chatapp.data.model.response;

import androidx.annotation.NonNull;

/**
 * Generic Result class holding result of data source access methods.
 */

public class Result<T> {

    public Result() {

    }

    public final static class Success<T> extends Result {

        private T data;

        public Success(@NonNull T data) {
            this.data = data;
        }

        @NonNull
        public T getData() {
            return data;
        }
    }


    public final static class Failure<T> extends Result {

        private T error;

        public Failure(@NonNull T error) {
            this.error = error;
        }

        @NonNull
        public T getError() {
            return error;
        }
    }

    public final static class Error extends Result {

        private Exception error;

        public Error(@NonNull Exception error) {
            this.error = error;
        }

        @NonNull
        public Exception getError() {
            return error;
        }
    }

    @Override
    public String toString() {
        if (this instanceof Result.Success) {
            Result.Success success = (Result.Success) this;
            return "Success[data=" + success.getData().toString() + "]";
        } else if (this instanceof Result.Failure) {
            Result.Failure failure = (Result.Failure) this;
            return "Failure[error=" + failure.getError().toString() + "]";
        } else if (this instanceof Result.Error) {
            Result.Error error = (Result.Error) this;
            return "Error[exception=" + error.getError().toString() + "]";
        }

        return "";
    }
}
