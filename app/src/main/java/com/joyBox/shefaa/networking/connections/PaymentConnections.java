package com.joyBox.shefaa.networking.connections;

import com.joyBox.shefaa.App;
import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.repositories.UserRepositoy;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Adhamkh on 2018-10-20.
 */

public class PaymentConnections {
    public static String getJsonPostWithDataJson(String url, String gSonData /*List<Pair<String, String>> params*/, int timeout) {
        HttpURLConnection c = null;

        try {
            URL u = new URL(url);
            c = (HttpURLConnection) u.openConnection();
            c.setConnectTimeout(timeout);
            c.setReadTimeout(NetworkingHelper.RequestTimeout);
            c.setRequestMethod("POST");
            c.setRequestProperty("charset", "utf-8");
            c.setDoInput(true);
            c.setDoOutput(true);
            c.setRequestProperty("Content-Type", "application/json");
            c.setUseCaches(false);
            c.setDefaultUseCaches(false);
            c.setRequestProperty("X-CSRF-Token", new UserRepositoy(App.app.getApplicationContext()).getClient().getToken());
//            JSONObject jsonParam = new JSONObject();
//            for (Pair<String, String> pr : params) {
//                jsonParam.put(pr.first, pr.second);
//            }
            DataOutputStream localDataOutputStream = new DataOutputStream(c.getOutputStream());
            localDataOutputStream.writeBytes(gSonData);
            localDataOutputStream.flush();
            localDataOutputStream.close();

            //c.connect();
            int status = c.getResponseCode();
            switch (status) {
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream(), "UTF-8"));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    br.close();
                    return sb.toString();
                case 400:
                case 401:
                    BufferedReader br1 = new BufferedReader(new InputStreamReader(c.getErrorStream(), "UTF-8"));
                    StringBuilder sb1 = new StringBuilder();
                    String line1;
                    while ((line1 = br1.readLine()) != null) {
                        sb1.append(line1 + "\n");
                    }
                    br1.close();
                    return sb1.toString();
            }
        } catch (Exception ex) {
            return NetworkingHelper.ErrorConnectionResponse;
        } finally {
            if (c != null) {
                try {
                    c.disconnect();
                } catch (Exception ex) {
                    return NetworkingHelper.ErrorConnectionResponse;
                }
            }
        }
        return NetworkingHelper.ErrorConnectionResponse;
    }

}
