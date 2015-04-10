package com.gpshealthsurvey.gpshealthsurvey;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;


public class CreateSurvey extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_survey);

        SurveyBuilderAdaptor adaptor = new SurveyBuilderAdaptor(new ArrayList<Attribute>());
        ListView fieldView = (ListView) findViewById(R.id.fieldView);
        fieldView.setAdapter(adaptor);

    }

    //TWO BIZARRE ERRORS: delete doesn't seem to work, probably because the view doesn't refresh each time you click a button in-item, and each time you change orientation it wipes the list...?


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_survey, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (id == R.id.action_add_field) {
            Attribute attribute = new Attribute();
            ListView fieldView = (ListView) findViewById(R.id.fieldView);
            SurveyBuilderAdaptor adaptor = (SurveyBuilderAdaptor) fieldView.getAdapter();
            adaptor.add(attribute);
            fieldView.setAdapter(adaptor);
        }

        if (id == R.id.action_clear_survey_builder){
            ListView fieldView = (ListView) findViewById(R.id.fieldView);
            SurveyBuilderAdaptor adaptor = (SurveyBuilderAdaptor) fieldView.getAdapter();
            adaptor.clear();
            fieldView.setAdapter(adaptor);
        }

        return super.onOptionsItemSelected(item);
    }
}
