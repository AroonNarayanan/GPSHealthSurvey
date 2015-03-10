package com.gpshealthsurvey.gpshealthsurvey;

import android.content.Intent;
import android.location.Location;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;


public class MarkHouse extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_house);
        locationLIBRARY locLIB = new locationLIBRARY(this);
        locLIB.getLocation();
        //continually request updates
        TextView latitude = (TextView) findViewById(R.id.latitude);
        TextView longitude = (TextView) findViewById(R.id.longitude);
        latitude.setText(Double.toString(locLIB.getLatitude()));
        longitude.setText(Double.toString(locLIB.getLongitude()));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mark_house, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_mark_house) {
            //PULL GPS LOCATION
            locationLIBRARY locLIB = new locationLIBRARY(this);
            Household markedHouse = new Household();
            locLIB.getLocation();
            markedHouse.latitude = locLIB.getLatitude();
            markedHouse.longitude = locLIB.getLongitude();

            Intent markIntent = new Intent(this,SurveyHouse.class);
            startActivity(markIntent);
        }

        return super.onOptionsItemSelected(item);
    }
}
