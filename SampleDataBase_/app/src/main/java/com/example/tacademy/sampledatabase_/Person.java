package com.example.tacademy.sampledatabase_;

/**
 * Created by Tacademy on 2016-04-29.
 */
public class Person  { // 저장할 객체
    long id=-1;
    String name;
    String  phone;
    String address;
    String office;

    @Override
    public String toString() {
        return name + "\n" +  phone + "\n" + address +"\n" +office;
    }
}