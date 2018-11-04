package com.example.kensi.infosys1d;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //to support POST/GET request policies
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //Default creations
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Input instantiations from UI
        final EditText inputEmail = findViewById(R.id.inputEmail);
        final EditText inputPassword = findViewById(R.id.inputPassword);
        final CheckBox checkRemember = findViewById(R.id.checkRemember);
        Button buttonSubmit = findViewById(R.id.buttonSubmit);
        final TextView replyPlaceholder = findViewById(R.id.replyPlaceholder);

        //Submission press
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String serverReply = LoginPostRequest.main(inputPassword.getText().toString(), inputEmail.getText().toString(), checkRemember.isChecked());
                if (serverReply.toCharArray()[11]=='1'){
                    replyPlaceholder.setText("Login Successful");
                }
                else if (serverReply.toCharArray()[11]=='-'){
                    replyPlaceholder.setText("Wrong E-mail/Password");
                }
                else{
                    replyPlaceholder.setText("Server Error");
                }


            }
        });

    }
}
