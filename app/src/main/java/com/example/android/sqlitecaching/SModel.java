package com.example.android.sqlitecaching;

public class SModel {

    private int id ;
    private String name, mail, age;


    public SModel(int id, String name, String mail, String age) {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }

    public String getAge() {
        return age;
    }
}
