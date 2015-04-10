package com.gpshealthsurvey.gpshealthsurvey;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class SurveyHouse extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_house);
        if(savedInstanceState == null){
            Bundle bundle = getIntent().getExtras();
            Household selectedHouse = (Household) bundle.get("Household");

            //populate fields with household properties
            TextView houseTitle = (TextView) findViewById(R.id.houseTitle_field);
            houseTitle.setText(selectedHouse.description);
            TextView houseVillage = (TextView) findViewById(R.id.houseVillage_field);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_survey_house, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handl    e clicks on the Home/Up button, so long




        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_save_survey) {
            //save survey
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
