package com.example.hongyi.lab10;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;

                if(ActivityCompat.checkSelfPermission(MapsActivity.this,
                        ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(MapsActivity.this,
                                ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    //詢問權限
                    ActivityCompat.requestPermissions(MapsActivity.this,
                            new String[] {ACCESS_FINE_LOCATION,ACCESS_COARSE_LOCATION },1
                    );

                    return;
                }
                ShowLabResult();


            }
        });
    }

    public void ShowLabResult() {
        try{
            mMap.setMyLocationEnabled(true);
        }catch(SecurityException e) {

        }

        MarkerOptions ml = new MarkerOptions();
        ml.position(new LatLng(25.033611,121.565000));
        ml.title("台北101");
        ml.draggable(true);
        mMap.addMarker(ml);

        MarkerOptions m2 = new MarkerOptions();
        m2.position(new LatLng(25.047924,121.517081));
        m2.title("台北車站");
        m2.draggable(true);
        mMap.addMarker(m2);

        PolylineOptions polylineOpt = new PolylineOptions();
        polylineOpt.add(new LatLng(25.033611,121.565000));
        polylineOpt.add(new LatLng(25.032728,121.565137));
        polylineOpt.add(new LatLng(25.033739,121.527886));
        polylineOpt.add(new LatLng(25.038716,121.517758));
        polylineOpt.add(new LatLng(25.045656,121.519636));
        polylineOpt.add(new LatLng(25.046200,121.517533));

        polylineOpt.color(Color.BLUE);
        Polyline polyline = mMap.addPolyline(polylineOpt);
        polyline.setWidth(10);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(25.033739,121.527886),11));
    }

    //處理使用者選擇後的結果
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    ShowLabResult();
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }
}
