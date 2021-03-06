package com.example.dialogues.app.models.pojos;

import com.example.dialogues.network.NetworkResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by vivek on 26/02/18.
 */

public class ItemResponse extends NetworkResponse{

    @SerializedName("data")
    @Expose
    ArrayList<Item> data;

    public ArrayList<Item> getData() {
        return data;
    }

    public void setData(ArrayList<Item> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ItemResponse{" +
                "data=" + data +
                '}';
    }
}
