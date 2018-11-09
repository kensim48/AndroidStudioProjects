package com.example.kensi.infosys1d;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //to support POST/GET request policies
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //Default creations
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Send a single POST request to establish connection
        LoginPostRequest.login("defaultpassword","default@email.com", false);

        //Input instantiations from UI
        final EditText inputEmail = findViewById(R.id.inputRegUser);
        final EditText inputPassword = findViewById(R.id.inputRegEmail);
        final CheckBox checkRemember = findViewById(R.id.checkRemember);
        final Button buttonSubmit = findViewById(R.id.buttonRegSubmit);
        final TextView replyPlaceholder = findViewById(R.id.replyPlaceholder);
        final Button buttonForget = findViewById(R.id.buttonForget);
        final Button buttonSign = findViewById(R.id.buttonSign);

        buttonSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this, Registration.class);
                startActivity(i);
            }
        });
        //Submission press
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = inputEmail.getText().toString();
                String errorMsg = LoginPostRequest.longChecker(email, inputPassword.getText().toString());
                if (!errorMsg.equals("no_error")) {

                    Toast.makeText(Login.this, errorMsg, Toast.LENGTH_LONG).show();
                } else {
                    String serverReply = LoginPostRequest.login(inputPassword.getText().toString(), email, checkRemember.isChecked());
                    replyPlaceholder.setText(serverReply);
                }
            }
        });
    }
}
