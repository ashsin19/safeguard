package com.exmple.safeguard;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.app.AlertDialog;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;

import com.exmple.besafe.R;

import java.util.Locale;

public class Loc extends Activity {

    LocationManager locationManager;
    double longitudeNetwork, latitudeNetwork;
    TextView longitudeValueNetwork, latitudeValueNetwork;
    SharedPreferences sp;
    Button b1,b2;
    Uri Uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loc);
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        sp=getSharedPreferences("login",MODE_PRIVATE);
        longitudeValueNetwork = (TextView) findViewById(R.id.longitudeValueNetwork);
        latitudeValueNetwork = (TextView) findViewById(R.id.latitudeValueNetwork);
        b1=(Button) findViewById(R.id.button5);
        b2=(Button) findViewById(R.id.button7);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loca=latitudeNetwork+","+longitudeNetwork;
                String phone1=sp.getString("number1", null);
                String phone2=sp.getString("number2", null);
                String phone3=sp.getString("number3", null);
                System.out.println(phone1);
                try {

                    SmsManager smsManager=SmsManager.getDefault();
                    smsManager.sendTextMessage(phone1, null, loca, null, null);
                    smsManager.sendTextMessage(phone2, null, loca, null, null);
                    smsManager.sendTextMessage(phone3, null, loca, null, null);
                    Toast.makeText(getApplicationContext(), "SMS Sent", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    // TODO: handle exception
                    Toast.makeText(getApplicationContext(), "SMS not Sent", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
       b2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?saddr=%f,%f&daddr=%f,%f",  latitudeNetwork, longitudeNetwork,null,null);
               Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
               intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
               startActivity(intent);
           }
       });
    }

    private boolean isLocationEnabled() {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }


    public void toggleNetworkUpdates(View view) {
        if(!checkLocation())
            return;
        Button button = (Button) view;
        if(button.getText().equals(getResources().getString(R.string.pause))) {
            locationManager.removeUpdates(locationListenerNetwork);
            button.setText(R.string.resume);
        }
        else {
            locationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER, 60 * 1000, 10, locationListenerNetwork);
            Toast.makeText(this, "Network provider started running", Toast.LENGTH_LONG).show();
            button.setText(R.string.pause);
        }
    }

    private final LocationListener locationListenerNetwork = new LocationListener() {
        public void onLocationChanged(Location location) {
            longitudeNetwork = location.getLongitude();
            latitudeNetwork = location.getLatitude();

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    longitudeValueNetwork.setText(longitudeNetwork + "");
                    latitudeValueNetwork.setText(latitudeNetwork + "");
                    Toast.makeText(Loc.this, "Network Provider update", Toast.LENGTH_SHORT).show();

                }
            });
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

    private boolean checkLocation() {
        if(!isLocationEnabled())
            showAlert();
        return isLocationEnabled();
    }

    private void showAlert() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Enable Location")
                .setMessage("Your Locations Settings is set to 'Off'.\nPlease Enable Location to " +
                        "use this app")
                .setPositiveButton("Location Settings", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(myIntent);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    }
                });
        dialog.show();
    }
}

