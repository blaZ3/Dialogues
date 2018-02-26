package com.example.dialogues.network.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by vivek on 26/02/18.
 */

public class Item {

    @SerializedName("itemId")
    @Expose
    String itemId;

    @SerializedName("desc")
    @Expose
    String desc;

    @SerializedName("audio")
    @Expose
    String audio;


    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId='" + itemId + '\'' +
                ", desc='" + desc + '\'' +
                ", audio='" + audio + '\'' +
                '}';
    }
}
