package com.example.tacademy.sampleapplicationcomponent;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int RC_MY = 0;
    private static final int  RC_CONTACTS = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyActivity.class);
                intent.putExtra(MyActivity.ERTRA_NAME, "value"); //MyActivity로 값 전달
                startActivityForResult(intent, RC_MY);
            }
        });

        btn = (Button) findViewById(R.id.btn_start);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyService.class); //서비스 시작
                startService(intent);

            }
        });

        btn = (Button) findViewById(R.id.btn_stop);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyService.class);
                stopService(intent); // 서비스 중단

            }
        });
        btn = (Button) findViewById(R.id.btn_read_contacts);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readContacts();

            }
        });
    }
    private void readContacts(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)  //권한 획득했는지 확인
                != PackageManager.PERMISSION_GRANTED){ //획득 안되면요청
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)){ //이 퍼미션을 사용자에게 이유를 설명하는 퍼미션
                //UI Display
                // OK  누르면 퍼미션 요청


            }else{
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, RC_CONTACTS); // request코드와 함게 퍼미션 요청

            }
            return;
        }

        //read contacts
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) { //퍼미션 결과
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == RC_CONTACTS){ //request 코드 확인
            if(permissions == null || permissions.length == 0){ //안했으면
                return;
            }
            if(permissions[0].equals(Manifest.permission.READ_CONTACTS) && grantResults[0] == PackageManager.PERMISSION_GRANTED){ //권한 허가했을 시

                readContacts(); //다시 요청
                return;

            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_MY) { // 내가 보낸 놈인지 확인하기 위해
            if (resultCode == Activity.RESULT_OK) { // 보낸곳에서 성공적으로 보냈는지
                String result = data.getStringExtra("result"); //MyActivity로에서 데이터 받기
                Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "실패", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
