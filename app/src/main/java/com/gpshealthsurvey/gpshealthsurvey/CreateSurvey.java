package com.gpshealthsurvey.gpshealthsurvey;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class CreateSurvey extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_survey);

        SurveyBuilderAdaptor adaptor = new SurveyBuilderAdaptor(new ArrayList<Attribute>());
        final ListView fieldView = (ListView) findViewById(R.id.fieldView);
        fieldView.setAdapter(adaptor);
        fieldView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

        fieldView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position,
                                                  long id, boolean checked) {
                // Here you can do something when items are selected/de-selected,
                // such as update the title in the CAB
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                // Respond to clicks on the actions in the CAB
                switch (item.getItemId()) {
                    case R.id.action_delete_field:
                        deleteItems();
                        mode.finish(); // Action picked, so close the CAB
                        return true;
                    default:
                        return false;
                }
            }

            public void deleteItems(){
                SparseBooleanArray checked = fieldView.getCheckedItemPositions();
                SurveyBuilderAdaptor curAdaptor = (SurveyBuilderAdaptor) fieldView.getAdapter();
                for (int i = (fieldView.getAdapter().getCount()-1); i > -1 ; i--){
                    if (checked.get(i)){
                        curAdaptor.delete(i);
                    }
                }
                fieldView.setAdapter(curAdaptor);
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                // Inflate the menu for the CAB
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.menu_create_survey_cab, menu);
                return true;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                // Here you can make any necessary updates to the activity when
                // the CAB is removed. By default, selected items are deselected/unchecked.
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                // Here you can perform updates to the CAB due to
                // an invalidate() request
                return false;
            }
        });

    }

    //TWO BIZARRE ERRORS: delete doesn't seem to work, probably because the view doesn't refresh each time you click a button in-item, and each time you change orientation it wipes the list...?
    //another one: spinner keeps resetting itself


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
        /*
        if (id == R.id.action_clear_survey_builder){
            ListView fieldView = (ListView) findViewById(R.id.fieldView);
            SurveyBuilderAdaptor adaptor = (SurveyBuilderAdaptor) fieldView.getAdapter();
            adaptor.clear();
            fieldView.setAdapter(adaptor);
        }
        */
        return super.onOptionsItemSelected(item);
    }
}
