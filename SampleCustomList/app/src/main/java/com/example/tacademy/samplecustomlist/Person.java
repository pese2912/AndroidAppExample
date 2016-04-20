package com.example.tacademy.samplecustomlist;

import android.graphics.drawable.Drawable;

public class Person  {

    String name;
    int age;
    Drawable photo;

    public Person(){
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Drawable getPhoto() {
        return photo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPhoto(Drawable photo) {
        this.photo = photo;
    }
}
