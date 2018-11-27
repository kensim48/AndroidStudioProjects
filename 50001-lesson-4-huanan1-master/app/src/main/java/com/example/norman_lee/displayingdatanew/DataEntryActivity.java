package com.example.norman_lee.displayingdatanew;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class DataEntryActivity extends AppCompatActivity {

    ImageView imageViewSelected;
    CharaDbHelper charaDbHelper;
    EditText editTextName;
    EditText editTextDescription;
    Bitmap bitmapSelected = null;
    Button buttonSelectImage;
    SQLiteDatabase db;
    int REQUEST_CODE_IMAGE = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);

        // 8.4 Get a reference to the CharaDbHelper
        charaDbHelper = CharaDbHelper.createCharaDbHelper(this);

        // 8.5 Get references to the widgets
        editTextName = findViewById(R.id.editTextNameEntry);
        editTextDescription = findViewById(R.id.editTextDescriptionEntry);
        buttonSelectImage = findViewById(R.id.buttonSelectImage);
        imageViewSelected = findViewById(R.id.imageViewSelected);

        // 8.6 when the selectImage button is clicked, set up an Implicit Intent to the gallery
        buttonSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentImageGallery = new Intent();
                intentImageGallery.setType("image/*");
                intentImageGallery.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intentImageGallery, REQUEST_CODE_IMAGE);
            }
        });

        //TODO 8.8 when the OK button is clicked, add the data to the db


    }

    //TODO 8.7 Complete OnActivityResult so that the selected image is displayed in the imageView
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_IMAGE && resultCode == Activity.RESULT_OK){
            Uri uri = data.getData();
            try {
                InputStream inputStream = this.getContentResolver().openInputStream(uri);
                bitmapSelected = Utils.convertStreamToBitmap(inputStream);
                imageViewSelected.setImageBitmap(bitmapSelected);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

}
