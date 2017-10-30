package com.dcu.anthonymurray.sda_a2_2018_anthonymurray;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity {
    String mCurrentPhotoPath;
    static final int REQUEST_TAKE_PHOTO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Set up global variables to enable photo taking and storage

    /*Call implicit intent to take a photo
Reference "https://developer.android.com/guide/components/intents-common.html#Camera"
Reference "https://developer.android.com/training/camera/photobasics.html"*/
    public void callCamera(View view)
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (intent.resolveActivity(getPackageManager()) != null)
        {
            File photo = null;

            try
            {
photo = createImageFile();
            }
            catch (IOException ex)
            {
                //handle error;
            }
            if (photo != null)
            {
                /*Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photo);

                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);

                startActivityForResult(intent, REQUEST_TAKE_PHOTO);
                */
            }
        }
    }


    /*Call implicit app to send an email
        Reference "https://developer.android.com/guide/components/intents-common.html#Email"*/
    public void sendMessage(String[] addresses, String subject) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /*A method to store a picture taken by the camera app
    Reference "https://developer.android.com/training/camera/photobasics.html"*/
    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";

        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        File image = File.createTempFile(imageFileName, ".jpg", storageDir);

        mCurrentPhotoPath = image.getAbsolutePath();

        return image;
    }
}