package com.example.kensi.postgettest;

import android.os.AsyncTask;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class PostClass extends AsyncTask<String, String, String> {

    public PostClass(){
        //set context variables if required
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        String urlString = params[0]; // URL to call
        String data = params[1]; //data to post
        OutputStream out = null;


        try {
            URL url = new URL("https://vanillacrust.chocolatepie.tech/admin/register");
            HttpURLConnection client = (HttpURLConnection) url.openConnection();
            out = new BufferedOutputStream(client.getOutputStream());
            client.setRequestMethod(“POST”);
            client.setRequestProperty(“Key”,”Value”);
            client.setDoOutput(true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return urlString;
    }
}
