package com.example.kensi.infosys1d;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class CheckoutRequest {
    public static String login() {
        try {
            String serverReply = login_call_me();
            return serverReply;
//            JSONObject jsonObj = new JSONObject(serverReply);
//            String ans = jsonObj.getString("data");
//            return ans;
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("MYAPP", "exception", e);
            return "check log";
        }
    }

    public static String login_call_me() throws Exception {
        String url = "https://chocolatepie.tech/inventory/listItem/1b4f108d388156ef80d9755f25b64f35313e90b33b6bcb31629ff9edcf67e6b96ad41753af48598167fae6a65d4ed9fb2888e761e7178e4aa19afe0eab1fed71";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }
}
