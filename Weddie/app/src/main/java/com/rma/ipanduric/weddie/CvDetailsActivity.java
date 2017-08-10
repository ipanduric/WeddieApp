package com.rma.ipanduric.weddie;

import android.*;
import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.util.Linkify;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class CvDetailsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener  {

    TextView tvIme, tvAdr, tvTel, tvAdr1, tvTel1;
    Bundle extra1, extra2, extra3, extra4, extra5;
    MapFragment cvMapFragment;
    LocationRequest cvLocationRequest;
    Marker cvCurrLocationMarker;


    GoogleMap cvGoogleMap;
    GoogleApiClient cvGoogleApiClient;
    LatLng marker;
    Marker mMarker;



    public static final String IME = "ime";
    public static final String ADR = "adr";
    public static final String TEL = "tel";
    public static final String LATITUDE = "lat";
    public static final String LONGITUDE = "lon";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cv_details);
        initialize();
        SetUp();

    }

    private void SetUp() {
        Intent startingIntent = this.getIntent();
        extra1 = startingIntent.getExtras();
        extra2 = startingIntent.getExtras();
        extra3 = startingIntent.getExtras();
        extra4 = startingIntent.getExtras();
        extra5 = startingIntent.getExtras();
        if (extra1 != null && extra2 != null && extra3 != null && extra4 != null && extra5 != null) {
            try {
                extra1.containsKey(IME);
                extra2.containsKey(ADR);
                extra3.containsKey(TEL);
                extra4.containsKey(LATITUDE);
                extra5.containsKey(LONGITUDE);
                String ime = extra1.getString(IME);
                String adr = extra2.getString(ADR);
                String tel = extra3.getString(TEL);
                String lat = extra4.getString(LATITUDE);
                String lon = extra5.getString(LONGITUDE);
                tvIme.setText(ime);
                tvAdr.setText(adr);
                tvTel.setText(tel);
                marker = new LatLng(Double.parseDouble(lat),Double.parseDouble(lon));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void initialize() {
        tvIme = (TextView) findViewById(R.id.tvIme);
        tvAdr = (TextView) findViewById(R.id.tvAdr);
        tvTel = (TextView) findViewById(R.id.tvTel);
        tvTel1 = (TextView) findViewById(R.id.tvTel1);
        tvAdr1 = (TextView) findViewById(R.id.tvAdr1);
        this.cvMapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.cvGoogleMap);
        this.cvMapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.cvGoogleMap = googleMap;

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(marker).title(extra1.getString(IME));;;
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
        mMarker = cvGoogleMap.addMarker(markerOptions);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                //Location Permission already granted
                buildGoogleApiClient();
                cvGoogleMap.setMyLocationEnabled(true);
            } else {
                //Request Location Permission
                checkLocationPermission();
            }
        } else {
            buildGoogleApiClient();
            cvGoogleMap.setMyLocationEnabled(true);
        }

    }

    protected synchronized void buildGoogleApiClient() {
        cvGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks((GoogleApiClient.ConnectionCallbacks) this)
                .addOnConnectionFailedListener((GoogleApiClient.OnConnectionFailedListener) this)
                .addApi(LocationServices.API)
                .build();
        cvGoogleApiClient.connect();
    }


    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 100;

    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle("Location Permission Needed")
                        .setMessage("Aplikacija treba vašu dozvolu za korištenje lokacije")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(CvDetailsActivity.this,
                                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (cvGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        cvGoogleMap.setMyLocationEnabled(true);
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "Pristup odbijen!", Toast.LENGTH_LONG).show();
                }
            }


            return;
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        cvLocationRequest = new LocationRequest();
        cvLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(cvGoogleApiClient, cvLocationRequest, (LocationListener) this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Tu ste");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        cvCurrLocationMarker = cvGoogleMap.addMarker(markerOptions);

        if (Geocoder.isPresent()) {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            try {
                List<Address> nearByAddresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(),1);
                if(nearByAddresses.size() > 0) {
                    StringBuilder stringBuilder = new StringBuilder();
                    android.location.Address nearestAddress = nearByAddresses.get(0);
                    stringBuilder.append(nearestAddress.getAddressLine(0)).append("\n")
                            .append(nearestAddress.getLocality()).append("\n")
                            .append(nearestAddress.getCountryName());
                }
            } catch (IOException e) { e.printStackTrace(); }
        }
    }
}


