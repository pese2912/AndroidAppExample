package com.example.tacademy.sampledrawerlayout;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView;
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_launcher);

        drawer = (DrawerLayout)findViewById(R.id.drawer);
        navigationView = (NavigationView)findViewById(R.id.navi_menu);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        //툴바로 액션바 정의

        drawer.addDrawerListener(new DrawerLayout.DrawerListener() {// drawer가 열렸는지 닫혔는지 확인 리스너
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                Toast.makeText(MainActivity.this, "Drawer Opened", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                Toast.makeText(MainActivity.this, "Drawer closed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //메뉴 선택시
        switch (item.getItemId()) {
            case android.R.id.home :
                drawer.openDrawer(GravityCompat.START); //drawer 닫음
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) { //네비게이션 아이템 선택시
        switch (item.getItemId()) {
            case R.id.menu_1 :
            case R.id.menu_2 :
            case R.id.menu_3 :
                Toast.makeText(this, "Menu Selected", Toast.LENGTH_SHORT).show();
                break;
        }
        drawer.closeDrawer(GravityCompat.START); //drawer 닫음
        return false;
    }

    @Override
    public void onBackPressed() { //백키 누르면 drawer 닫음
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}