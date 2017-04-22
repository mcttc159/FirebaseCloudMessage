package com.nxt.firebasecloudmessage;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by NXT on 14/10/2016.
 */

public class FireBaseIDTask extends AsyncTask<String, Void, Boolean> {
    @Override
    protected Boolean doInBackground(String... params) {

        try {

           // Log.d("toke",params[0]);


            URL url = new URL("http://10.9.1.39/FirebaseServer/api/fcm/?token=" + params[0]);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/xml;charset=UTF-8");
            InputStream inputStream = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder builder = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null) {
                builder.append(line);
                line = bufferedReader.readLine();
            }

            Log.d("ketqua",builder.toString());
            boolean kt = builder.toString().contains("true");
            return kt;




        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Loi", e.toString());
        }


        return null;
    }
}
