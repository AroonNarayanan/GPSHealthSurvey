package com.gpshealthsurvey.gpshealthsurvey;

import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.location.Location;
import android.net.Uri;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class MarkHouse extends ActionBarActivity {

    //houses loaded into the ListView
    final ArrayList<Household> SampleHouseArray = new ArrayList<>();

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

        Household A = new Household("Narayanan house",35.34090584144,-83.5612943116575);
        Household B = new Household("Dhingra house",35.35090584144,-83.5712943116575);
        Household C = new Household("Raffaele house",35.35090584144,-83.5812943116575);
        Household D = new Household("Williams house",35.34090584144,-83.5612943116575);
        Household E = new Household("Paucar house",35.35090584144,-83.5712943116575);
        Household F = new Household("Patel house",35.35090584144,-83.5812943116575);
        Household G = new Household("Somu house",35.34090584144,-83.5612943116575);
        Household H = new Household("Appel house",35.35090584144,-83.5712943116575);
        Household I = new Household("Correa house",35.35090584144,-83.5812943116575);
        Household J = new Household("Skinkle house",35.34090584144,-83.5612943116575);
        Household K = new Household("Bannerjee house",35.35090584144,-83.5712943116575);
        Household L = new Household("Zemel house",35.35090584144,-83.5812943116575);
        SampleHouseArray.add(A);
        SampleHouseArray.add(B);
        SampleHouseArray.add(C);
        SampleHouseArray.add(D);
        SampleHouseArray.add(E);
        SampleHouseArray.add(F);
        SampleHouseArray.add(G);
        SampleHouseArray.add(H);
        SampleHouseArray.add(I);
        SampleHouseArray.add(J);
        SampleHouseArray.add(K);
        SampleHouseArray.add(L);

        //connect house listview to our sample list
        HouseAdaptor adaptor = new HouseAdaptor(SampleHouseArray);
        ListView houseView = (ListView) findViewById(R.id.houseList);
        houseView.setAdapter(adaptor);
        houseView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Household selectedHouse = SampleHouseArray.get(position);
                Intent markIntent = new Intent(parent.getContext(),SurveyHouse.class);
                markIntent.putExtra("Household",selectedHouse);
                startActivity(markIntent);
            }
        });
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
    public boolean onContextItemSelected(MenuItem item){
        int id = item.getItemId();
        //WARNING: this might get buggy if SampleHouseArray isn't properly kept in sync with the listview - put this in a Try{}
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Household selectedHouse = SampleHouseArray.get(menuInfo.position);
        if(id==R.id.navigate){
            Intent navIntent = new Intent(Intent.ACTION_VIEW);
            Uri uri = Uri.parse("geo:0,0?q=" + Double.toString(selectedHouse.latitude) + "," + Double.toString(selectedHouse.longitude) + "("+selectedHouse.getDescription()+")");
            navIntent.setData(uri);
            startActivity(navIntent);

        }
        return super.onContextItemSelected(item);
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
