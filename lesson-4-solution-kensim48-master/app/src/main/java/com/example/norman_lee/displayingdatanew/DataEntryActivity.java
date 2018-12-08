package com.example.norman_lee.displayingdatanew;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
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
    SQLiteDatabase db;
    int REQUEST_CODE_IMAGE = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);

        //TODO 8.4 Get a reference to the CharaDbHelper
        charaDbHelper = CharaDbHelper.createCharaDbHelper(this);

        //TODO 8.5 Get references to the widgets
        editTextName = findViewById(R.id.editTextNameEntry);
        editTextDescription = findViewById(R.id.editTextDescriptionEntry);
        Button buttonSelectImage = findViewById(R.id.buttonSelectImage);
        imageViewSelected = findViewById(R.id.imageViewSelected);

        //TODO 8.6 when the selectImage button is clicked, set up an Implicit Intent to the gallery
        buttonSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_CODE_IMAGE);
            }
        });

        //TODO 8.8 when the OK button is clicked, add the data to the db
        Button buttonOK = findViewById(R.id.buttonOK);
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = editTextName.getText().toString();
                String description = editTextDescription.getText().toString();

                if( bitmapSelected == null){
                    Toast.makeText(DataEntryActivity.this,
                            "no image selected",
                            Toast.LENGTH_LONG).show();
                }else{

                    CharaDbHelper.CharaData charaData
                            = new CharaDbHelper.CharaData(name,description,bitmapSelected);
                    charaDbHelper.insertOneRow( charaData );

                    Toast.makeText(DataEntryActivity.this,
                            "inserting to database",
                            Toast.LENGTH_LONG).show();

                    //In MainActivity we started DataEntryActivity using startActivityForResult
                    //Hence,DataEntryActivity must return a result and hence this set of code
                    Intent returnIntent = new Intent();
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                }

            }
        });


    }

    //TODO 8.7 Complete OnActivityResult so that the selected image is displayed in the imageView
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if( requestCode == REQUEST_CODE_IMAGE
                && resultCode == Activity.RESULT_OK){
            //do stuff here - recipe code follows, don't fret ..
            try{
                Uri uri = data.getData();
                InputStream inputStream = this.getContentResolver()
                        .openInputStream(uri);
                bitmapSelected = Utils.convertStreamToBitmap(inputStream);
                imageViewSelected.setImageBitmap(bitmapSelected);
            }catch(FileNotFoundException ex){
                ex.printStackTrace();
            }

        }
    }
}
