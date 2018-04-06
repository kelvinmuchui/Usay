package com.example.kelvin.mychat.Model;

import com.example.kelvin.mychat.data.ListFriend;

/**
 * Created by kelvin on 4/1/18.
 */

public class Group extends Room {
    public String id;
    public ListFriend listFriend;

    public Group(){
        listFriend = new ListFriend();
    }
}
