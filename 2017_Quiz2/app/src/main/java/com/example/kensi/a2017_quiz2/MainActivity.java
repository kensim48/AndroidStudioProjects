package com.example.kensi.a2017_quiz2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Main";

    JSONArray jsonArray;
    SQLiteDatabase folksongsDatabase;
    String input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText editText = findViewById(R.id.text);
        Button button = findViewById(R.id.button);
        TextView result = findViewById(R.id.result);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input = editText.toString();
                parseJson(input);
            }
        });


        //TODO 3.1 - update the XML Layout file to meet your needs
        //TODO 3.2 - see below
        //TODO 3.3 - complete this MainActivity class to meet the requirements of the question
    }

    private String convertJsonToString(int resource) {

        String line;
        String output = "";

        InputStream inputStream = getResources().openRawResource(resource);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        try {
            while ((line = reader.readLine()) != null) {
                output = output + line;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return output;
    }

    //TODO 3.2 - complete this method
    protected void parseJson(String input) {

        String jsonString = convertJsonToString(R.raw.folksongs);
        try {
            Log.i(TAG, "parseJson: " + jsonString);
            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); i++) {
                String title = jsonArray.getJSONObject(i).getString("title");
                Log.i(TAG, "parseJson: " + title);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}


