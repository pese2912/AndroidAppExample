package com.example.tacademy.samplecompoundwidget;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PersonView personView = (PersonView)findViewById(R.id.pv);
        Person p = new Person();

        p.setAge(25);
        p.setName("Kim Sang Su");
        p.setPhoto(ContextCompat.getDrawable(this,R.mipmap.ic_launcher));
        personView.setPerson(p);


        personView.setOnImageClickListener(new PersonView.OnImageClickListener(){

            @Override
            public void onImageClick(PersonView personView, Person person) {
                Toast.makeText(MainActivity.this, person.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
