package com.example.kensi.androidapplesson01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

//TODO 1.2 Go to activity_main.xml and put in the layout
public class MainActivity extends AppCompatActivity {
    ArrayList<Integer> images;
    Button charaButton;
    ImageView charaImage;
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        images = new ArrayList<>();
        images.add(R.drawable.e);
        images.add(R.drawable.download);
        images.add(R.drawable.download1);
        images.add(R.drawable.s);
        //TODO 1.3 Instantiate an ArrayList object
        //TODO 1.4 Add the image IDs to the ArrayList
        //TODO 1.5 Get references to charaButton and charaImage
        charaButton = findViewById(R.id.charaButton);
        charaImage = findViewById(R.id.charaImage);

        //TODO 1.6 For charaButton, invoke the

        //TODO 1.7 Create an anonymous class which implements
//        View.OnClickListener
        //TODO 1.8 Write code to randomly select an image ID from
        charaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = (count )% images.size();
                count = count + 1;
                int id = images.get(index);
                charaImage.setImageResource(id);
            }
        });
        //TODO 1.9 [On your own] Create another Button to always
//        display the first image
    }
}