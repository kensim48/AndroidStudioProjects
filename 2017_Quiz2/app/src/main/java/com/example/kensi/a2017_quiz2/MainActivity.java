package com.example.kensi.a2017_quiz2;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    JSONArray jsonArray;
    FolksongsDbHelper folksongsDatabase;
    private String TAG = "a";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parseJson();
        final EditText editText = findViewById(R.id.text);
        Button button = findViewById(R.id.button);
        final TextView result = findViewById(R.id.result);
        folksongsDatabase = FolksongsDbHelper.createFolksongsDbHelper(this,jsonArray);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = editText.getText().toString();
                if (input.length()>=2){
                    String a =input.substring(1).toLowerCase();
                    String b = String.valueOf(input.charAt(0)).toUpperCase();
                    input = b+a;
                    Log.i(TAG, "onClick:aaa "+input);
                }
                String ans =  folksongsDatabase.queryOneRow(input);
                result.setText(ans);
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
    protected void parseJson() {

        String jsonString = convertJsonToString(R.raw.folksongs);
        try{
            jsonArray = new JSONArray(jsonString);
//            folksongsDatabase = FolksongsDbHelper.createFolksongsDbHelper(this,jsonArray);
        } catch (Exception e) {
            Log.i(TAG, "parseJson: aaaaaaa");
        }

    }
}
