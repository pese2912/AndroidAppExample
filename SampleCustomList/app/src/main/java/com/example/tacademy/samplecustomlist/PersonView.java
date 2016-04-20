package com.example.tacademy.samplecustomlist;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Tacademy on 2016-04-19.
 */

public class PersonView extends FrameLayout{
    public PersonView(Context context)
    {
        this(context, null);
    }
    TextView nameView, ageView;
    ImageView photoView;

    public PersonView(Context context, AttributeSet attrs)
    {
        super(context,attrs);

        inflate(context,R.layout.view_person,this);

        nameView = (TextView)findViewById(R.id.text_name);
        ageView = (TextView)findViewById(R.id.text_age);
        photoView = (ImageView)findViewById(R.id.image_photo);

        photoView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onImageClick(PersonView.this, person);
                }
            }

        });

    }

    Person person;

    public   void setPerson(Person p){
        person = p;
        nameView.setText(p.getName());
        ageView.setText(""+p.getAge());
        photoView.setImageDrawable(p.getPhoto());
    }

    public  boolean isOld()
    {
        if(person ==null) return false;
        return person.getAge() > 40? true:false;
    }

    public interface OnImageClickListener{ //인터페이스 정의
        public void onImageClick(PersonView personView, Person person);
    }

    OnImageClickListener mListener;
    public void setOnImageClickListener(OnImageClickListener listener){
        mListener = listener;
    }


}
