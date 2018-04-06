package com.example.kelvin.mychat.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kelvin on 4/1/18.
 */

public class Room {
    public ArrayList<String> member;
    public Map<String, String> groupInfo;

    public Room(){
        member = new ArrayList<>();
        groupInfo = new HashMap<String, String>();
    }
}
