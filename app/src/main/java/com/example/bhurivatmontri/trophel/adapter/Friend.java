package com.example.bhurivatmontri.trophel.adapter;

/**
 * Created by Bhurivat Montri on 11/2/2017.
 */

public class Friend {

    private String name;
    private String detail;
    private String friendID;
    private int icon;

    public Friend(String name,String detail,String friendID,int icon){
        this.setName(name);
        this.setDetail(detail);
        this.setFriendID(friendID);
        this.setIcon(icon);
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }
    public void setDetail(String detail) { this.detail = detail; }

    public String getFriendID() {
        return friendID;
    }
    public void setFriendID(String friendID) {
        this.friendID = friendID;
    }

    public int getIcon() {
        return icon;
    }
    public void setIcon(int icon) {
        this.icon = icon;
    }
}
