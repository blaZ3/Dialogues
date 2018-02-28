package com.example.dialogues.app.models.pojos;

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

    public Item() {}

    public Item(String itemId, String desc, String audio){
        this.itemId = itemId;
        this.desc = desc;
        this.audio = audio;
    }

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
