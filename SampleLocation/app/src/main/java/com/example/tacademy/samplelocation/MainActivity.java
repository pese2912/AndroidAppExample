package com.example.tacademy.samplelocation;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.audiofx.BassBoost;
import android.media.audiofx.EnvironmentalReverb;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    LocationManager mLM;
    TextView messageView;
    String mProvider = LocationManager.GPS_PROVIDER; //에뮬레이션 밖에서사용하는 위치


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLM = (LocationManager) getSystemService(Context.LOCATION_SERVICE); //위치정보 매니저
        messageView = (TextView) findViewById(R.id.text_message);

        if (!mLM.isProviderEnabled(mProvider)) { //위치사용 안되있으면
            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)); //위치사용

        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        updateLocation();
    }

    private void updateLocation() {
        if (!mLM.isProviderEnabled(mProvider)) {
            return;
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED  //퍼미션있는지 체크
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {// 퍼미션 동의서
                AlertDialog.Builder builder = new AlertDialog.Builder(this); //alert로 요청 메시지 띄움
                builder.setTitle("Location Permission");
                builder.setMessage("permission..");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        requestPermission();
                    }
                });
                builder.create().show();
                return;
            }
            requestPermission();
            return;
        }

        Location location = mLM.getLastKnownLocation(mProvider); // 가장 마지막 픽스된 로케이션
        
        if(location != null){
            displayLocation(location);
        }



        mLM.requestLocationUpdates(mProvider, 5000, 5, mListener); // 위치 변경

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
        }
        mLM.removeUpdates(mListener); // 위치 스탑
    }

    LocationListener mListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {// 변경됬을 시
            displayLocation(location); // 다시 보여줌

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    private void displayLocation(Location location){
        messageView.setText("lat : "+ location.getLatitude() + ", lng : "+ location.getLongitude());
    }

    private static final int  RC_FINE_LOCATION = 100;

    private void requestPermission(){ //동적 퍼미션 요청
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},RC_FINE_LOCATION );


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) { //퍼미션 결과
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == RC_FINE_LOCATION){
            if(permissions != null && permissions.length > 0){
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){ // 퍼미션 획득시
                    updateLocation();// 다시 진행
                }
            }
        }
    }
}
