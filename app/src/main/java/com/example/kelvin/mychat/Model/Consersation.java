package com.example.kelvin.mychat.Model;

import java.util.ArrayList;

/**
 * Created by kelvin on 4/1/18.
 */

public class Consersation {
    private ArrayList<Message> listMessageData;
    public Consersation(){
        listMessageData = new ArrayList<>();
    }

    public ArrayList<Message> getListMessageData() {
        return listMessageData;
    }
}