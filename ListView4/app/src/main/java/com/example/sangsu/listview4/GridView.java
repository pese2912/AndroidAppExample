package com.example.sangsu.listview4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

public class GridView extends AppCompatActivity {
    GridView gridView;
   ArrayAdapter<ImageView> arr;
    int[] IDS = {R.mipmap.ic_launcher,};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);





    }
}
