package com.gpshealthsurvey.gpshealthsurvey;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.os.Environment.getExternalStorageDirectory;


public class SurveyHouse extends ActionBarActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    //maybe we should find a better directory
    static final Uri mLocationForPhotos = Uri.parse(getExternalStorageDirectory().toString());
    public Camera mCamera = null;
public Uri filePath = null;
    public int rotation = 0;

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
        rotation = this.getWindowManager().getDefaultDisplay()
                .getRotation();
        final SurfaceView surfaceView = (SurfaceView) findViewById(R.id.cameraPreview);

        //launch camera by tapping the image
        //TODO: error when trying to take a second image
        final ImageView houseImage = (ImageView) findViewById(R.id.houseImage);
        houseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //show preview surface
                surfaceView.setVisibility(View.VISIBLE);
                //launch camera
                if(safeCameraOpen(0)){
                        //orient camera correctly
                        //TODO: this doesn't work
                        int degrees = 0;
                        android.hardware.Camera.CameraInfo info =
                            new android.hardware.Camera.CameraInfo();
                        switch (rotation) {
                            case Surface.ROTATION_0: degrees = 0; break;
                            case Surface.ROTATION_90: degrees = 90; break;
                            case Surface.ROTATION_180: degrees = 180; break;
                            case Surface.ROTATION_270: degrees = 270; break;
                        }
                        mCamera.setDisplayOrientation((info.orientation - degrees + 360) % 360);

                        SurfaceHolder holder = surfaceView.getHolder();
                        holder.addCallback(new SurfaceHolder.Callback() {
                            @Override
                            public void surfaceCreated(SurfaceHolder holder) {
                                try {
                                    mCamera.setPreviewDisplay(holder);
                                    mCamera.startPreview();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            }

                            @Override
                            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

                            }

                            @Override
                            public void surfaceDestroyed(SurfaceHolder holder) {

                            }
                        });
                }


            }
        });

        //take photo by tapping on preview
        surfaceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //save photo
                mCamera.takePicture(null, null, new Camera.PictureCallback() {
                    @Override
                    public void onPictureTaken(byte[] data, Camera camera) {
                        //save file
                        //TODO: come up with better naming scheme
                        File photo = new File(Environment.getExternalStorageDirectory(), "test.jpg");
                        if(photo.exists()){
                            photo.delete();
                        }

                        try {
                            FileOutputStream fileOutputStream = new FileOutputStream(photo.getPath());
                            fileOutputStream.write(data);
                            fileOutputStream.close();

                            filePath = Uri.parse(photo.getPath());

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException f) {
                            f.printStackTrace();
                        }
                        //release preview
                        releaseCamera();
                        surfaceView.setVisibility(View.GONE);
                        //set thumbnail
                        if(filePath != null){
                            Bitmap bm = BitmapFactory.decodeFile(photo.getPath());
                            houseImage.setImageBitmap(bm);
                        }

                    }
                });

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

    private boolean safeCameraOpen(int id) {
        boolean qOpened = false;

        try {
            releaseCamera();
            mCamera = Camera.open(id);
            qOpened = (mCamera != null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return qOpened;
    }

    private void releaseCamera() {
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.setPreviewCallback(null);
            mCamera.release();
            mCamera = null;
        }
    }
}
