package com.example.kelvin.mychat.Model;

/**
 * Created by kelvin on 4/4/18.
 */

public class AllUsers {

    public String name;
    public String avata;

    public AllUsers(){

    }

    public AllUsers(String name, String avata) {
        this.name = name;
        this.avata = avata;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvata() {
        return avata;
    }

    public void setAvata(String avata) {
        this.avata = avata;
    }
}
