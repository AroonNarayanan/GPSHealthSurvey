package com.gpshealthsurvey.gpshealthsurvey;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import static android.os.Environment.getExternalStorageDirectory;


public class SurveyHouse extends ActionBarActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    //maybe we should find a better directory
    static final Uri mLocationForPhotos = Uri.parse(getExternalStorageDirectory().toString());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_house);
        if(savedInstanceState == null){
            Bundle bundle = getIntent().getExtras();
            Household selectedHouse = (Household) bundle.get("Household");

            //populate fields with household properties passed in from intent
            TextView houseTitle = (TextView) findViewById(R.id.houseTitle_field);
            houseTitle.setText(selectedHouse.description);
            TextView houseVillage = (TextView) findViewById(R.id.houseVillage_field);
        }

        //launch camera by tapping the image
        ImageView houseImage = (ImageView) findViewById(R.id.houseImage);
        houseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //launch camera intent
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT,
                        //need to come up with a naming scheme
                        Uri.withAppendedPath(mLocationForPhotos, "testIMG"));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
                }


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            //set thumbnail
            Bitmap thumbnail = data.getParcelableExtra("data");
            ImageView houseImage = (ImageView) findViewById(R.id.houseImage);
            houseImage.setImageBitmap(thumbnail);
            // Do other work with full size photo saved in mLocationForPhotos

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
