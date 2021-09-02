package com.example.chatapp.data.model.response;

import androidx.annotation.NonNull;

/**
 * Holds service response code
 *
 */
public class ResponseCode {

    private String code;

    public final static String ERROR             = "-1000";
    public final static String SUCCESS_CODE      = "200";
    public final static String REDIRECT_CODE     = "300";
    public final static String CLIENT_ERROR_CODE = "400";
    public final static String SERVER_ERROR_CODE = "500";

    public ResponseCode(@NonNull int code) {
        this.code = String.valueOf(code);
    }

    @NonNull
    public String getCode() {
        return code;
    }

    @NonNull
    public String getCodeType() {
        int code = Integer.parseInt(this.code);

        if (Integer.parseInt(SUCCESS_CODE) <= code
                && code < Integer.parseInt(REDIRECT_CODE)) {
            return SUCCESS_CODE;
        }
        else if (Integer.parseInt(CLIENT_ERROR_CODE) <= code
                && code < Integer.parseInt(SERVER_ERROR_CODE)) {
            return CLIENT_ERROR_CODE;
        }
        else if (Integer.parseInt(ERROR) == code) {
            return ERROR;
        }
        else return ERROR;
    }

    @Override
    public String toString() {
        if (getCodeType() == SUCCESS_CODE) {
            return "Success: " + getCode();
        }
        else if (getCodeType() == CLIENT_ERROR_CODE) {
            return "Client error: " + getCode();
        }
        return "";
    }
}
