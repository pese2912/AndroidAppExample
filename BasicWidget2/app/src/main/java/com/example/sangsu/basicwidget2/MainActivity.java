package com.example.sangsu.basicwidget2;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText emailView;
    EditText passwordView;
    ProgressBar hProgressBar;
    SeekBar seekBar;

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    InputMethodManager mIMM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // InputMehtodManager 획득
        mIMM = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        emailView = (EditText)findViewById(R.id.edit_email);
        passwordView = (EditText)findViewById(R.id.edit_password);




        passwordView.setOnEditorActionListener(new TextView.OnEditorActionListener() { //다음 버튼을 눌렀을시
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    String email = emailView.getText().toString();
                    if (email.matches(EMAIL_PATTERN)) { // 이메일 형식이 맞으면
                        Toast.makeText(MainActivity.this, "서버 전송", Toast.LENGTH_SHORT).show();
                        // SoftInput을 사라지게 함.
                        mIMM.hideSoftInputFromWindow(passwordView.getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);

                    } else {
                        Toast.makeText(MainActivity.this, "형식 불일치", Toast.LENGTH_SHORT).show();
                    }
                }
                return true;
            }
        });

        passwordView.addTextChangedListener(new TextWatcher() { //EditText가 변경될때
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = s.toString();
                if (text.length() < 8) {
                    passwordView.setTextColor(Color.RED);
                } else {
                    passwordView.setTextColor(Color.BLACK);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        hProgressBar = (ProgressBar)findViewById(R.id.progressBar4);
        // Progress의 Max값을 설정
        hProgressBar.setMax(10000);
        // SecondaryProgress 값 설정. 필요없으면 설정하지 않아도 됨.
        hProgressBar.setSecondaryProgress(5000);
        // Progress 값 설정.
        hProgressBar.setProgress(3000);



        seekBar = (SeekBar)findViewById(R.id.seekBar1);
        seekBar.setMax(100);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "변경 후", Toast.LENGTH_SHORT).show();
                // 사용자가 Progress 변경을 끝냈을 때 호출
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "변경 시작", Toast.LENGTH_SHORT).show();
                // 사용자가 Thumb를 선택하여 Progress변경을 시작할 때 호출
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                // Progress가 변경되면 호출. 사용자가 UI를 이용해 변경한 경우 fromUser가 true, code상으로 변경한 경우 fromUser가 false
            }
        });




    }
}
