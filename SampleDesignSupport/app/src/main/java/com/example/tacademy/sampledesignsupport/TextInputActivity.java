package com.example.tacademy.sampledesignsupport;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TextInputActivity extends AppCompatActivity {

    FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_input);

        floatingActionButton = (FloatingActionButton)findViewById(R.id.fab);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "FAB CLICK ", Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
