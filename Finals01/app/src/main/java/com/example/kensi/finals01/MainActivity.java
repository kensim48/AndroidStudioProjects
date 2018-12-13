package com.example.kensi.finals01;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    EditText editTextFaceValue;
    EditText editTextSellingPrice;
    EditText editTextAnnualInterest;
    EditText editTextDuration;
    Button buttonCalculateYield;
    TextView textViewResult;

    private SharedPreferences mPreferences;
    final String sharedPrefFile = "sharedPref";
    SharedPreferences sharedPreferences;
    final String KEY_FACEVALUE = "faceValue";
    final String KEY_SELLINGPRICE = "sellingPrice";
    final String KEY_ANNUALINTEREST = "annualInterest";
    final String KEY_DURATION = "duration";
    final String EMPTY_STRING = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextAnnualInterest = findViewById(R.id.editTextAnnualInterest);
        editTextDuration = findViewById(R.id.editTextDuration);
        editTextFaceValue = findViewById(R.id.editTextFaceValue);
        editTextSellingPrice = findViewById(R.id.editTextSellingPrice);
        buttonCalculateYield = findViewById(R.id.buttonCalculateYield);
        textViewResult = findViewById(R.id.textViewResult);

        mPreferences = getSharedPreferences(sharedPrefFile,MODE_PRIVATE);
        String face = mPreferences.getString("face","");
        String sell = mPreferences.getString("sell","");
        String ann = mPreferences.getString("ann","");
        String dura = mPreferences.getString("dura","");

        editTextSellingPrice.setText(sell);
        editTextAnnualInterest.setText(ann);
        editTextFaceValue.setText(face);
        editTextDuration.setText(dura);




        buttonCalculateYield.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editTextFaceValue.getText().toString().equals("") || editTextSellingPrice.getText().toString().equals("") || editTextAnnualInterest.getText().toString().equals("") || editTextDuration.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "One of the fields is empty", Toast.LENGTH_LONG).show();
                } else {


                    double[] nums = new double[4];
                    try {
                        nums[0] = Double.valueOf(editTextFaceValue.getText().toString());
                        nums[1] = Double.valueOf(editTextSellingPrice.getText().toString());
                        nums[2] = Double.valueOf(editTextAnnualInterest.getText().toString());
                        nums[3] = Double.valueOf(editTextDuration.getText().toString());
                    } catch (Exception E) {
                        Toast.makeText(MainActivity.this, "One of the fields is not numeric", Toast.LENGTH_LONG).show();
                    }
                    GetYield getYield = new GetYield();
                    getYield.execute(nums);
                }
            }
        });


    }

    class GetYield extends AsyncTask<double[], String, Double> {
        @Override
        protected Double doInBackground(double[]... nums) {
            Bond.BondBuilder bondBuilder = new Bond.BondBuilder();
            Bond bond = bondBuilder
                    .setDuration(nums[0][3])
                    .setFaceValue(nums[0][0])
                    .setSellingPrice(nums[0][1])
                    .setInterestPayment(nums[0][2]).createBond();
            if (nums[0][2] != 0) {
                bond.setYieldCalculator(new WithCouponYield());
            } else {
                bond.setYieldCalculator(new ZeroCouponYield());
            }

            return bond.calculateYTM();
        }

        @Override
        protected void onPostExecute(Double d) {

            textViewResult.setText(String.valueOf(d));
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }
    }



    protected void onPause () {
        super .onPause();
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        String face = editTextFaceValue.getText().toString();
        String sell = editTextSellingPrice.getText().toString();
        String ann = editTextAnnualInterest.getText().toString();
        String dura = editTextDuration.getText().toString();
        preferencesEditor.putString("face", face);
        preferencesEditor.putString("sell", sell);
        preferencesEditor.putString("ann", ann);
        preferencesEditor.putString("dura", dura);
        preferencesEditor.apply();
    }
}
