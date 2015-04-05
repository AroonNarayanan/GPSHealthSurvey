package com.gpshealthsurvey.gpshealthsurvey;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class HomeScreen extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        //Add items to home screen menu
        ArrayList<com.gpshealthsurvey.gpshealthsurvey.MenuListItem> menuItems = new ArrayList<com.gpshealthsurvey.gpshealthsurvey.MenuListItem>();
        com.gpshealthsurvey.gpshealthsurvey.MenuListItem markWaypoint = new com.gpshealthsurvey.gpshealthsurvey.MenuListItem("Places","mark a place with GPS","mark");
        com.gpshealthsurvey.gpshealthsurvey.MenuListItem sync = new com.gpshealthsurvey.gpshealthsurvey.MenuListItem("Sync","upload and download data","sync");
        com.gpshealthsurvey.gpshealthsurvey.MenuListItem create = new com.gpshealthsurvey.gpshealthsurvey.MenuListItem("Create Survey","create and modify surveys","create");
        com.gpshealthsurvey.gpshealthsurvey.MenuListItem sample = new com.gpshealthsurvey.gpshealthsurvey.MenuListItem("Sample Data","produce statistical samples from database","sample");
        //com.gpshealthsurvey.gpshealthsurvey.MenuListItem navigate = new com.gpshealthsurvey.gpshealthsurvey.MenuListItem("Navigate","navigate to a household","nav");
        menuItems.add(markWaypoint);
        menuItems.add(sync);
        menuItems.add(sample);
        menuItems.add(create);
        //menuItems.add(navigate);

        //Attach menu items to click listeners
        com.gpshealthsurvey.gpshealthsurvey.MenuAdaptor adaptor = new com.gpshealthsurvey.gpshealthsurvey.MenuAdaptor(menuItems);
        ListView menuView = (ListView) findViewById(R.id.menuList);
        menuView.setAdapter(adaptor);
        menuView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    //Mark Household
                    Intent markIntent = new Intent(parent.getContext(),MarkHouse.class);
                    startActivity(markIntent);
                }
                if(position==1) {
                    //Sync Data
                    Intent syncIntent = new Intent(parent.getContext(),Sync.class);
                    startActivity(syncIntent);
                }
                if(position==2) {
                    //Sample Data
                    Intent sampleIntent = new Intent(parent.getContext(),SampleData.class);
                    startActivity(sampleIntent);
                }
                if(position==3) {
                    //Create Survey
                    Intent surveyIntent = new Intent(parent.getContext(),CreateSurvey.class);
                    startActivity(surveyIntent);
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
           // Intent settingsIntent = new Intent(this,SettingsActivity.class);
           // startActivity(settingsIntent);
        }

        return super.onOptionsItemSelected(item);
    }
}
