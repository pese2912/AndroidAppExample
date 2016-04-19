package com.example.tacademy.samplebasicwidget;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.CompoundButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CheckBox checkBox;
    EditText pass;
    RadioGroup group;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView =(TextView)findViewById(R.id.test_text);
        textView.setSelected(true);

        checkBox = (CheckBox)findViewById(R.id.check);


        checkBox.setOnCheckedChangeListener(new  CompoundButton.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isForced) return;
                Toast.makeText(MainActivity.this, "isChecked : " + isChecked , Toast.LENGTH_SHORT).show();
            }
        });


        group = (RadioGroup)findViewById(R.id.group_sex);

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_m :
                    case R.id.radio_f :
                        break;
                }
            }
        });

       pass = (EditText)findViewById(R.id.pass);
        final EditText email = (EditText)findViewById(R.id.email);

        pass.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if(actionId == EditorInfo.IME_ACTION_SEND){
                    String addr = email.getText().toString();

                        Toast.makeText(MainActivity.this,"서버전송",Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });

        pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = s.toString();
                if (text.length() < 4) {
                    pass.setTextColor(Color.RED);
                } else {
                    pass.setTextColor(Color.BLACK);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }


        });


    }

    boolean isForced = false;
    public void onMyButtonClick(View view) {
        Toast.makeText(this, "My Button Click", Toast.LENGTH_SHORT).show();
        isForced = true;
        checkBox.setChecked(!checkBox.isChecked());
        isForced = false;

        int id = group.getCheckedRadioButtonId();
        switch (id) {
            case R.id.radio_f :
                group.check(R.id.radio_m);
                break;
            case R.id.radio_m :
                group.check(R.id.radio_f);
                break;
        }
    }

}
