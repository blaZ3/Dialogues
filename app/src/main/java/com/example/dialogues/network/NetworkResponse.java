package com.example.dialogues.network;

/**
 * Created by vivek on 26/02/18.
 */

public class NetworkResponse {
    private static final String TAG = NetworkResponse.class.getSimpleName();

    public enum Status{
        SUCCESS,
        API_ERROR,
        NETWORK_ERROR
    }

    Status status;

    public NetworkResponse() {}

    public NetworkResponse(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
