package com.example.tacademy.sampledatabase_;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DataAddActivity extends AppCompatActivity {

    EditText nameView, phoneView, addressView, officeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_add);
        nameView = (EditText)findViewById(R.id.edit_name);
        phoneView = (EditText)findViewById(R.id.edit_phone);
        addressView = (EditText)findViewById(R.id.edit_address);
        officeView = (EditText)findViewById(R.id.edit_office);

        Button btn = (Button)findViewById(R.id.btn_data_add);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Person p = new Person();
                p.name = nameView.getText().toString();
                p.phone = phoneView.getText().toString();
                p.address = addressView.getText().toString();
                p.office = officeView.getText().toString();
                DataManager.getInstance().insert(p);
                nameView.setText("");
                phoneView.setText("");
                addressView.setText("");
                officeView.setText("");
            }
        });
    }
}