package com.gpshealthsurvey.gpshealthsurvey;

import android.app.AlertDialog;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

/**
 * Created by cwilliams on 2/17/15.
 */
public class locationLIBRARY extends Service implements LocationListener {

    private final Context mContext;
    boolean isGPSEnabled = false;
    boolean GPSstatus = false;

    Location location;
    double latitude;
    double longitude;

    private static final long MIN_DISTANCE = 10; //10 meters to have different data
    private static final long MIN_TIME = 1000 * 60 * 1; // 1 minutes intervals between updates

    protected LocationManager locationManager;

    //class constructor
    public locationLIBRARY(Context context){
        this.mContext = context;
        getLocation();
    }

    public Location getLocation(){
        try{
            locationManager = (LocationManager) mContext
                    .getSystemService(LOCATION_SERVICE);

            //checking for GPS
            isGPSEnabled = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);

            if(isGPSEnabled){
                locationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        MIN_TIME,
                        MIN_DISTANCE, this);
                Log.d("GPS Enabled", "GPS Enabled");
                if(locationManager != null){
                    location = locationManager
                            .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    if(location != null){
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                    }
                }
            }
            else{
                //display a toast
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return location;
    }

    public void stopGPS(){
        if(locationManager != null){
            locationManager.removeUpdates(locationLIBRARY.this);
        }
    }

    public double getLatitude(){
        if(location != null){
            latitude = location.getLatitude();
        }

        return latitude;
    }

    public double getLongitude(){
        if(location != null){
            longitude = location.getLongitude();
        }

        return longitude;
    }

    public boolean GPSstatus(){
        return this.GPSstatus;
    }

    public void alertGPSNotOn(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);

        // Setting Dialog Title
        alertDialog.setTitle("GPS is setting");

        //Dialog message
        alertDialog.setMessage("GPS is not enabled. Would you like to go to settings menu?");

        //if settings button was pressed
        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                mContext.startActivity(intent);
            }
        });

        //if cancel was pressed
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
    }

    @Override
    public void onLocationChanged(Location location) {

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

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

