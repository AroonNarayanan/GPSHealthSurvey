package com.gpshealthsurvey.gpshealthsurvey;

import android.content.Intent;
import android.location.Location;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class MarkHouse extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_house);
        locationLIBRARY locLIB = new locationLIBRARY(this);
        locLIB.getLocation();
        //TODO: continually request updates
        TextView latitude = (TextView) findViewById(R.id.latitude);
        TextView longitude = (TextView) findViewById(R.id.longitude);
        latitude.setText(String.format("%.3f", locLIB.getLatitude()));
        longitude.setText(String.format("%.3f", locLIB.getLongitude()));

        //sample list of households
        ArrayList<Household> SampleHouseArray = new ArrayList<>();
        Household A = new Household("Narayanan house",35.34090584144,-83.5612943116575);
        Household B = new Household("Dhingra house",35.35090584144,-83.5712943116575);
        Household C = new Household("Raffaele house",35.35090584144,-83.5812943116575);
        SampleHouseArray.add(A);
        SampleHouseArray.add(B);
        SampleHouseArray.add(C);

        //connect house listview to our sample list
        HouseAdaptor adaptor = new HouseAdaptor(SampleHouseArray);
        ListView houseView = (ListView) findViewById(R.id.houseList);
        houseView.setAdapter(adaptor);
        registerForContextMenu(houseView);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,v,menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.house_context_menu,menu);
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

            locLIB.getLocation();
            Household markedHouse = new Household(locLIB.getLatitude(),locLIB.getLongitude());
            Intent markIntent = new Intent(this,SurveyHouse.class);
            markIntent.putExtra("Household",markedHouse);
            startActivity(markIntent);
        }

        return super.onOptionsItemSelected(item);
    }
}
