package com.example.posicionamentoglobal_gps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.gps.MinhaLocalizacaoListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void buscarInformacoesGPS(View v) {
        LocationManager mLocManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        LocationListener mLocListener = new com.example.gps.MinhaLocalizacaoListener();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mLocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mLocListener);
            if (mLocManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                String text;
                text = "Latitude: " + MinhaLocalizacaoListener.latitude + "\n" + "Longitude: " + MinhaLocalizacaoListener.longitude + "\n";
                Toast.makeText(MainActivity.this, text, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(MainActivity.this,"GPS DESABILITADO.", Toast.LENGTH_LONG).show();
            }

            this.mostrarGoogleMaps(MinhaLocalizacaoListener.latitude, MinhaLocalizacaoListener.longitude);
        } else {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_NETWORK_STATE}, 1);
        }

    }
    public void mostrarGoogleMaps(double latitude, double longitude){
        WebView wv;
        wv = findViewById(R.id.webv);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.loadUrl("https://www.google.com/maps/search/?api=1&query=" + latitude + "," + longitude);
    }





}