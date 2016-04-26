package com.example.tacademy.sampledesignsupport;

import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationView = (NavigationView)findViewById(R.id.menu);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        TextView nameView = (TextView)headerView.findViewById(R.id.textView);
        nameView.setText("YSI");
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_1 :
            case R.id.menu_2 :
            case R.id.menu_3 :
                Toast.makeText(this, "menu item selected : "+ item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
        }
        return false;
    }

}