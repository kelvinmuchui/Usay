package com.example.kelvin.mychat.data;

import com.example.kelvin.mychat.Model.Friend;

import java.util.ArrayList;

/**
 * Created by kelvin on 4/1/18.
 */

public class ListFriend {
    private ArrayList<Friend> listFriend;

    public ArrayList<Friend> getListFriend() {
        return listFriend;
    }

    public ListFriend(){
        listFriend = new ArrayList<>();
    }

    public String getAvataById(String id){
        for(Friend friend: listFriend){
            if(id.equals(friend.id)){
                return friend.avata;
            }
        }
        return "";
    }

    public void setListFriend(ArrayList<Friend> listFriend) {
        this.listFriend = listFriend;
    }
}
