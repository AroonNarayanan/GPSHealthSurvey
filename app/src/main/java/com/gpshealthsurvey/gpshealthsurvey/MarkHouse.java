package com.gpshealthsurvey.gpshealthsurvey;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MarkHouse extends ActionBarActivity {
    private HouseholdDataSource datasource;
    private HouseAdaptor adaptor;

    //houses loaded into the ListView
    public ArrayList<Household> SampleHouseArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_house);
        getLocation();



    }

    public void getLocation(){
        //display location on status panel
        ProgressDialog fetchingLocation = new ProgressDialog(this);
        //TODO: put this in a string resource
        fetchingLocation.setMessage("Fetching your current location...");
        fetchingLocation.show();
        locationLIBRARY locLIB = new locationLIBRARY(this);
        locLIB.getLocation();
        //TODO: continually request updates
        TextView latitude = (TextView) findViewById(R.id.latitude);
        TextView longitude = (TextView) findViewById(R.id.longitude);
        TextView accuracy = (TextView) findViewById(R.id.accuracy);
        latitude.setText(String.format("%.3f", locLIB.getLatitude()));
        longitude.setText(String.format("%.3f", locLIB.getLongitude()));
        accuracy.setText(String.format("%.1f", locLIB.getAccuracy()) + "m");
        fetchingLocation.hide();
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
        if(id==R.id.delete_place){
            //Prompt the user with a dialog to make sure they want to delete
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            //TODO: replace these with string resources
            builder.setMessage("Are you sure you want to delete this place?")
                    .setTitle(R.string.delete);
            builder.setPositiveButton(R.string.yes,new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //delete place
                }
            });
            builder.setNegativeButton(R.string.no,new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //dismiss dialog, do nothing
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
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
    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first

        // Create DataSource Object
        datasource = new HouseholdDataSource(this);
        datasource.open();
        // call method in household data source to select all households and return array
        SampleHouseArray = datasource.getAllHouseholds();



        LinearLayout statusPanel = (LinearLayout) findViewById(R.id.statusPanel);
        statusPanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLocation();
            }
        });

        //sample list of households

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
}
