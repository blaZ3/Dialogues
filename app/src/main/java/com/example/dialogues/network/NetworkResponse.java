package com.example.dialogues.network;

/**
 * Created by vivek on 26/02/18.
 */

public class NetworkResponse {


    public enum Status{
        SUCCESS,
        API_ERROR,
        NETWORK_ERROR
    }

    Status status;
    Object data;

    public NetworkResponse(Status status, Object data) {
        this.status = status;
        this.data = data;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
