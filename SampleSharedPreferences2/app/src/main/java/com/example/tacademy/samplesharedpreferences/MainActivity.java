package com.example.tacademy.samplesharedpreferences;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    SharedPreferences mPrefs;
    SharedPreferences.Editor mEditor;
    private static final String PREF_NAME = "settings";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";

    EditText emailView, passwordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emailView = (EditText)findViewById(R.id.edit_email);
        passwordView = (EditText)findViewById(R.id.edit_password);

        //mPrefs = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
//        mEditor = mPrefs.edit();
//
//        emailView.setText(mPrefs.getString(KEY_EMAIL, ""));
//        passwordView.setText(mPrefs.getString(KEY_PASSWORD, ""));

        emailView.setText(PropertyManager.getInstance().getEmail());
        passwordView.setText(PropertyManager.getInstance().getPassword());

        Button btn = (Button)findViewById(R.id.btn_save);
        btn.setOnClickListener(new View.OnClickListener() {  //파일에 저장
            @Override
            public void onClick(View v) {
                String email = emailView.getText().toString();
                String passwrod = passwordView.getText().toString();
//                mEditor.putString(KEY_EMAIL, email);
//                mEditor.putString(KEY_PASSWORD, password);
//                mEditor.commit();
                PropertyManager.getInstance().setEmail(email);
                PropertyManager.getInstance().setPassword(passwrod);

            }
        });
    }
}
