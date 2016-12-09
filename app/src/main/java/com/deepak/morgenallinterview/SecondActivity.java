package com.deepak.morgenallinterview;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    TextView latText, longText, updateLatText, updateLongText;
    Button updateButton;
    LinearLayout linearLayout1;

    LocationManager locationManager;
    LocationListener locationListener;

    double latitude1, longitude1, latitude2, longitude2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        latText = (TextView) findViewById(R.id.latTextView1);
        longText = (TextView) findViewById(R.id.longTextView1);

        updateLatText = (TextView) findViewById(R.id.latTextView2);
        updateLongText = (TextView) findViewById(R.id.longTextView2);
        updateButton = (Button) findViewById(R.id.updateButton);
        linearLayout1 = (LinearLayout) findViewById(R.id.container1);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        latitude1 = bundle.getDouble("latitude");
        longitude1 = bundle.getDouble("longitude");

        latText.setText("" + latitude1);
        longText.setText("" + longitude1);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                linearLayout1.setBackgroundColor(getResources().getColor(R.color.yellow));
                latitude2 = location.getLatitude();
                longitude2 = location.getLongitude();
                updateLatText.setText("" + latitude2);
                updateLongText.setText("" + longitude2);
                if(latitude1 != latitude2 || longitude1 != longitude2){
                    linearLayout1.setBackgroundColor(getResources().getColor(R.color.green));
                }
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        locationManager.requestLocationUpdates("gps", 0, 1, locationListener);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                latText.setText(""+latitude2);
                longText.setText(""+longitude2);
                linearLayout1.setBackgroundColor(getResources().getColor(R.color.yellow));
            }
        });

    }
}
