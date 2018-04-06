package com.example.kelvin.mychat.data;

import com.example.kelvin.mychat.Model.User;

import java.util.ArrayList;

/**
 * Created by kelvin on 4/2/18.
 */

public class ListUser {
    private ArrayList<User> listUser;

    public ArrayList<User> getListUser() {
        return listUser;
    }

    public ListUser(){
        listUser = new ArrayList<>();
    }

    public String getAvataById(String id){
        for(User friend: listUser){
            if(id.equals(friend.id)){
                return friend.avata;
            }
        }
        return "";
    }

    public void setListUser(ArrayList<User> listUser) {
        this.listUser = listUser;
    }
}
