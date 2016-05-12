package com.example.tacademy.samplegooglelocation;


import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements
        GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks,
        OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener,
        GoogleMap.OnInfoWindowClickListener,
        GoogleMap.OnMapLongClickListener{

    GoogleApiClient mClient;
    TextView messageView;
    TextView infoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        messageView = (TextView) findViewById(R.id.text_message);
        infoView = (TextView)findViewById(R.id.text_info);

        mClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .enableAutoManage(this, this)
                .addConnectionCallbacks(this)
                .build();
        SupportMapFragment fragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_fragment);
        fragment.getMapAsync(this);
    }

    GoogleMap mMap;



    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Location location = LocationServices.FusedLocationApi.getLastLocation(mClient);
        displayMessage(location);
        LocationRequest request = new LocationRequest();
        request.setInterval(10000);
        request.setFastestInterval(5000);
        request.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        LocationServices.FusedLocationApi.requestLocationUpdates(mClient, request, mListener);
    }

    LocationListener mListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            displayMessage(location);
        }
    };

    private void displayMessage(Location location) {
        if (location != null) {
            messageView.setText("lat : " + location.getLatitude() + ", lng : " + location.getLongitude());
            moveMap(location.getLatitude(), location.getLongitude(), 15f);

        }
    }

    private void moveMap(double lat, double lng , float zoom){// 맵이동

        CameraPosition position = new CameraPosition.Builder()
                .target(new LatLng(lat,lng))
                .zoom(zoom)
   //             .bearing(30)
    //            .tilt(30)
                .build();
        CameraUpdate update = CameraUpdateFactory.newCameraPosition(position);


        //CameraUpdate update = CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), zoom);

        if (mMap != null) {
            //mMap.animateCamera(update); //애니메이션 이동
            mMap.moveCamera(update); // 바로 이동

        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //  mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); //위성사진
        mMap.getUiSettings().setZoomControlsEnabled(true);
       // if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
       //     return;
        //}
     //   mMap.setMyLocationEnabled(true); //내위치로 이동
        mMap.setOnMapLongClickListener(this);
        mMap.setOnMarkerClickListener(this);
        mMap.setOnInfoWindowClickListener(this);
    }
    @Override
    public void onMapLongClick(LatLng latLng) { //롱클릭시
        addMarker(latLng.latitude, latLng.longitude);
    }
    private void addMarker(double lat, double lng){ //마커 추가
        MarkerOptions options = new MarkerOptions();
        options.position(new LatLng(lat,lng));
        options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
        options.anchor(0.5f, 1f);
        options.title("MyMarker");
        options.snippet("marker description");
        options.draggable(true); //마커 드래그 가능
        Marker m = mMap.addMarker(options);



    }

    @Override
    public boolean onMarkerClick(Marker marker) { //마커 클릭시
        infoView.setText(marker.getTitle() + "\n\r" + marker.getSnippet());
        marker.showInfoWindow(); //강제로 띄움
        return true;//true 시 인포윈도우 안띄움

    }

    @Override
    public void onInfoWindowClick(Marker marker) { // 인포윈도우 액션후 사라지게
        Toast.makeText(this, "marker : "+ marker.getTitle() , Toast.LENGTH_SHORT).show();
        marker.hideInfoWindow();

    }
}