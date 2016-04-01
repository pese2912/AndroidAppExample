package com.example.sangsu.basicwidget3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {
    DatePicker datePicker;
    TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        datePicker = (DatePicker)findViewById(R.id.datePicker1);
        timePicker = (TimePicker)findViewById(R.id.timePicker1);
        // 값 설정
        datePicker.init(2014, 10, 10, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // 설정 값을 얻어옴

            }
        });

        // 값 설정.
        timePicker.setCurrentHour(14);
        timePicker.setCurrentMinute(10);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                // 설정 값을 얻어옴.
            }
        });


    }
}
