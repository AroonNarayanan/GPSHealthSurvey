package com.gpshealthsurvey.gpshealthsurvey;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;


public class HomeScreen extends ActionBarActivity {

    static final int REQUEST_IMAGE_GET = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        //Add items to home screen menu
        //TODO: these should be string resources
        ArrayList<com.gpshealthsurvey.gpshealthsurvey.MenuListItem> menuItems = new ArrayList<com.gpshealthsurvey.gpshealthsurvey.MenuListItem>();
        com.gpshealthsurvey.gpshealthsurvey.MenuListItem markWaypoint = new com.gpshealthsurvey.gpshealthsurvey.MenuListItem("Places","mark a place with GPS","mark");
        com.gpshealthsurvey.gpshealthsurvey.MenuListItem sync = new com.gpshealthsurvey.gpshealthsurvey.MenuListItem("Sync","upload and download data","sync");
        com.gpshealthsurvey.gpshealthsurvey.MenuListItem create = new com.gpshealthsurvey.gpshealthsurvey.MenuListItem("Create Survey","create and modify surveys","create");
        com.gpshealthsurvey.gpshealthsurvey.MenuListItem sample = new com.gpshealthsurvey.gpshealthsurvey.MenuListItem("Sample Data","randomly sample places","sample");
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

            //super temporary:
//            FieldTemplate fieldTemplate = new FieldTemplate();
//            fieldTemplate.field_name = "testName";
//            fieldTemplate.field_type = "text";
//            fieldTemplate.field_value = "lkddad";
//
//            SurveyTemplate surveyTemplate = new SurveyTemplate();
//            ArrayList<FieldTemplate> stuff = new ArrayList<FieldTemplate>();
//            stuff.add(fieldTemplate);
//            surveyTemplate.fields = stuff;
//            surveyTemplate.name = "sjl";
//
//            Serializer serializer = new Persister();
//            File outFile = new File(Environment.getExternalStorageDirectory(),"test.xml");
//            try {
//                serializer.write(surveyTemplate,outFile);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }

        }

        //import survey XML
        //TODO: make survey XML template persist
        if (id == R.id.action_load){
            Intent loadIntent = new Intent(Intent.ACTION_GET_CONTENT);
            loadIntent.setType("application/xml");
            if (loadIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(loadIntent, REQUEST_IMAGE_GET);
            }
        }

        if (id == R.id.action_export){
            
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        if (requestCode == REQUEST_IMAGE_GET && resultCode == RESULT_OK){
            //save XML
            Uri originalXML_uri = data.getData();
            File originalXML = new File(originalXML_uri.getPath());
            File surveyXML = new File(Environment.getExternalStorageDirectory(),"survey.xml");
            if(surveyXML.exists()){
                surveyXML.delete();
            }
            try {
                copy(originalXML,surveyXML);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void copy(File src, File dst) throws IOException {
        InputStream in = new FileInputStream(src);
        OutputStream out = new FileOutputStream(dst);

        // Transfer bytes from in to out
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        in.close();
        out.close();
    }
}
