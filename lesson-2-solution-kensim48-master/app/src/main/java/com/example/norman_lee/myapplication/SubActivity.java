package com.example.norman_lee.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SubActivity extends AppCompatActivity {

    Button buttonBackToCalculator;
    EditText editTextSubValueOfA;
    EditText editTextSubValueOfB;
    public final static String INTENT_EXCH_RATE = "Exchange Rate";
    private SharedPreferences mPreferences;
    private String sharedPrefFile = "com.example.android.subsharedprefs";
    public final static String A_KEY = "A_KEY";
    public final static String B_KEY = "B_KEY";
    public final static String TAG = "Norman";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);


        //TODO 4.9 Implement saving to shared preferences for the contents of the EditText widget
        //TODO 4.10 below
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        String A_text = mPreferences.getString(A_KEY,"");
        String B_text = mPreferences.getString(B_KEY,"");

        //TODO 3.5 Get references to the editText widgets
        //TODO 3.6 Get a reference to the Back To Calculator (OK) Button
        //TODO 3.7 Set up setOnClickListener
        //TODO 3.8 Obtain the values stored in the editTextWidgets
        editTextSubValueOfA = findViewById(R.id.editTextSubValueA);
        editTextSubValueOfB = findViewById(R.id.editTextSubValueB);

        //TODO 4.9 continues here *******
        editTextSubValueOfA.setText(A_text);
        editTextSubValueOfB.setText(B_text);

        buttonBackToCalculator = findViewById(R.id.buttonBackToCalculator);
        buttonBackToCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String valueA = editTextSubValueOfA.getText().toString();
                String valueB = editTextSubValueOfB.getText().toString();
                try{
                    double exchangeRate = ExchangeRate.calculateExchangeRate(valueA, valueB);
                    Log.i(TAG,"valueA:" + valueA + " Value B:" + valueB
                            + " exchange:" + exchangeRate);
                    Intent intent = new Intent(SubActivity.this, MainActivity.class);
                    intent.putExtra(INTENT_EXCH_RATE, exchangeRate );
                    startActivity(intent);
                }catch(ArithmeticException ex){
                    Toast.makeText(SubActivity.this,
                            "Divide by zero",
                            Toast.LENGTH_LONG).show();
                }catch(NumberFormatException ex){
                    Toast.makeText(SubActivity.this,
                            "Please enter a value",
                            Toast.LENGTH_LONG).show();
                }



            }
        });

        //TODO 3.9 Calculate the exchange rate --> see the ExchangeRate class
        //TODO 3.10 Set up an explicit intent and pass the exchange rate back to MainActivity
        //TODO 3.11 Decide how you are going to handle a divide-by-zero situation
        //TODO 3.12 Decide how you are going to handle a situation when the editText widgets are empty

    }

    //TODO 4.10 Don't forget to override onPause()


    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putString(A_KEY, editTextSubValueOfA.getText().toString());
        preferencesEditor.putString(B_KEY, editTextSubValueOfB.getText().toString());
        preferencesEditor.apply();
    }
}
