package com.github.hbtruong2017.oft.ui.appointment;

import com.github.hbtruong2017.oft.R;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Looper;

import com.github.hbtruong2017.oft.api.model.Appointment;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class MapActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private GoogleMap mGoogleMap;
    private SupportMapFragment mapFrag;
    private LocationRequest mLocationRequest;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private Marker mCurrLocationMarker;
    private FusedLocationProviderClient mFusedLocationClient;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private double latitude;
    private double longitude;
    private TextView addressTv;
    static Appointment appointment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // Bottom bar
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.next:
                        Intent intent = new Intent(MapActivity.this, DateActivity.class);

                        // Pass data in Intent
                        appointment = new Appointment();
                        TextView addressTextView = (TextView) findViewById(R.id.address);
                        appointment.setAddress(addressTextView.getText().toString().substring(9));
                        intent.putExtra("appointment", appointment);

                        startActivity(intent);
                        break;
                }
                return true;
            }
        });

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFrag.getMapAsync(this);

    }

    @Override
    public void onPause() {
        super.onPause();

        //stop location updates when Activity is no longer active
        if (mFusedLocationClient != null) {
            mFusedLocationClient.removeLocationUpdates(mLocationCallback);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mGoogleMap =  googleMap;
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        UiSettings uiSettings = mGoogleMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
        uiSettings.setCompassEnabled(true);

        //Initialize Google Play Services
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                //Location Permission already granted
                buildGoogleApiClient();
                mGoogleMap.setMyLocationEnabled(true);
            } else {
                requestPermissions(new String[]{
                                android.Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
                return ;
            }
        }
        else {
            buildGoogleApiClient();
            mGoogleMap.setMyLocationEnabled(true);
        }
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setInterval(3000); // one second interval
        mLocationRequest.setFastestInterval(3000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
        }
    }

    LocationCallback mLocationCallback = new LocationCallback(){
        @Override
        public void onLocationResult(LocationResult
                                             locationResult) {

            for (Location location : locationResult.getLocations()) {
                addressTv = (TextView) findViewById(R.id.address);
                latitude=location.getLatitude();
                longitude=location.getLongitude();
                addressTv.setText(getAddressFromLatAndLong());

                mLastLocation = location;
                if (mCurrLocationMarker != null) {
                    mCurrLocationMarker.remove();
                }
                //Place current location marker
                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                setLocationMarker(latLng);
            }
        }

    };

    private String getAddressFromLatAndLong(){
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addressList  = geocoder.getFromLocation(latitude,longitude, 1);
            if(addressList!=null && addressList.size()>0){
                String address = addressList.get(0).getAddressLine(0);
                return "Address: " + address;
            }else{
                return "";
            }
        }catch (IOException ioe){
            ioe.printStackTrace();
            return "";
        }
    }

    @Override
    public void onConnectionSuspended(int i) {}

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {}

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mGoogleMap.setMyLocationEnabled(true);
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                break;

            }

            // other 'case' lines to check for other
            // permissions this app might request
        }


    }

    public void searchForDiffLoc(View view) {
        TextView newLoc = findViewById(R.id.diffLoc);
        String newAddress = newLoc.getText().toString();
        Geocoder geocoder = new Geocoder(this);
        List<Address> listAddress;
        LatLng loc = null;

        try {
            listAddress = geocoder.getFromLocationName(newAddress, 5);
            if (newAddress != null) {
                Address location = listAddress.get(0);
                loc = new LatLng(location.getLatitude(), location.getLongitude());
                longitude=location.getLongitude();
                latitude=location.getLatitude();
                addressTv.setText(getAddressFromLatAndLong());
                if (mCurrLocationMarker != null) {
                    mCurrLocationMarker.remove();
                }
                setLocationMarker(loc);
                if (mFusedLocationClient != null) {
                    mFusedLocationClient.removeLocationUpdates(mLocationCallback);
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (IndexOutOfBoundsException ioobe){
            Toast.makeText(this, "Please enter a valid address!", Toast.LENGTH_SHORT).show();
            newLoc.setText("");
            ioobe.printStackTrace();
        }
    }

    private void setLocationMarker(LatLng latLng){
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        mCurrLocationMarker = mGoogleMap.addMarker(markerOptions);

        //move map camera
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
    }

}
