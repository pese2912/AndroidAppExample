package com.example.tacademy.samplecompoundwidget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

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
