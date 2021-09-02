package com.example.chatapp.data.model;

import com.example.chatapp.data.model.response.Result;

import org.junit.Test;

import java.io.IOException;

import static junit.framework.Assert.assertEquals;

public final class ResultTest {

    @Test
    public void resultSuccessToString_returnsTokenString() {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9." +
                "eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ." +
                "SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
        String expected = "Success[data=" + token + "]";

        Token jwt = new Token(token);
        Result<Token> success = new Result.Success<>(jwt);
        String actual = success.toString();

        assertEquals(expected, actual);
    }

    @Test
    public void resultErrorToString_returnsExceptionString() {
        String errorMessage = " Error logging in";
        String expected = "Error[exception=java.io.IOException: "
                + errorMessage + "]";

        Exception exception = new Exception();
        Result<Token> error = new Result.Error(new IOException(errorMessage, exception));
        String actual = error.toString();

        assertEquals(expected, actual);
    }
}
