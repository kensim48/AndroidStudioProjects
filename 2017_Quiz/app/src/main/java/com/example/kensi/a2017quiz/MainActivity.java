package com.example.kensi.a2017quiz;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URL;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView textViewResult;
    EditText editTextNumIters;
    Button buttonRun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextNumIters = findViewById(R.id.EditText);
        textViewResult = findViewById(R.id.EstPi);
        buttonRun = findViewById(R.id.Button);
        //TODO 1 - implement the callback and other necessary tasks
        buttonRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EstimatePi estimatePi = new EstimatePi();
                if (editTextNumIters.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Please enter a value", Toast.LENGTH_LONG).show();
                } else {
                    try {
                        estimatePi.execute(Integer.valueOf(editTextNumIters.getText().toString()));
                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, "Please enter a value", Toast.LENGTH_LONG).show();
                    }

                }
            }
        });


    }

    //TODO 2 - Implement the necessary methods
    class EstimatePi extends AsyncTask<Integer, Double[], Double> {
        protected Double doInBackground(Integer... n) {
            double currPi;
            int count = 0;
            for (int i = 0; i < n[0]; i++) {
                double x = Math.random();
                double y = Math.random();
                if (Math.pow(x, 2) + Math.pow(y, 2) <= 1) {
                    count++;
                }
                if (i % 100000 == 0) {
                    currPi = 4.0 * (Double.valueOf(count) / Double.valueOf(n[0]));
                    Double[] a = {Double.valueOf(i), currPi};
                    publishProgress(a);
                }
            }
            return 4.0 * (Double.valueOf(count) / Double.valueOf(n[0]));
        }

        protected void onProgressUpdate(Double[]... progress) {
            textViewResult.setText("After " + String.valueOf(progress[0][0]) + " iterations the estimate of pi is " + String.valueOf(progress[0][1]));
        }

        protected void onPostExecute(Double i) {
            textViewResult.setText("Iterations are complete, answer is: " + String.valueOf(i));
            Toast.makeText(MainActivity.this, "Iterations are complete", Toast.LENGTH_LONG).show();
        }

    }


}
