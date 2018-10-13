package com.joyBox.shefaa.networking.connections;

import android.util.Log;
import android.util.Pair;

import com.joyBox.shefaa.App;
import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.repositories.UserRepositoy;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by Adhamkh on 2018-08-18.
 */

public class NotificationConnections {

    public static String registerNotificationToken(String url, List<Pair<String, String>> params, int timeout) {
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
            c.setUseCaches(false);
            c.setDefaultUseCaches(false);
            c.setRequestProperty("X-CSRF-Token", new UserRepositoy(App.app.getApplicationContext()).getClient().getToken());
//            for (HttpCookie cookie : JsonParser.cookies) {
//                c.setRequestProperty("Cookie", cookie.getValue());
//            }
            c.setRequestProperty("Content-Type", "application/json");

            JSONObject jsonParam = new JSONObject();
            for (Pair<String, String> pr : params) {
                jsonParam.put(pr.first, pr.second);
            }
            DataOutputStream localDataOutputStream = new DataOutputStream(c.getOutputStream());
            localDataOutputStream.writeBytes(jsonParam.toString());
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
//                case 401:
//                    BufferedReader br1 = new BufferedReader(new InputStreamReader(c.getErrorStream(), "UTF-8"));
//                    StringBuilder sb1 = new StringBuilder();
//                    String line1;
//                    while ((line1 = br1.readLine()) != null) {
//                        sb1.append(line1 + "\n");
//                    }
//                    br1.close();
//                    return sb1.toString();
            }
        } catch (MalformedURLException ex) {
            return NetworkingHelper.ErrorConnectionResponse;
        } catch (IOException ex) {
            return NetworkingHelper.ErrorConnectionResponse;
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

    public static String getNotificationsList(String url, int timeout) {

        HttpURLConnection c = null;
        try {
            URL u = new URL(url);
            c = (HttpURLConnection) u.openConnection();
//            CookieManager cookieManager = new CookieManager(null, CookiePolicy.ACCEPT_ALL);
//            List<HttpCookie> httpCookieList = cookieManager.getCookieStore().getCookies();

            c.setInstanceFollowRedirects(false);

            c.setRequestMethod("GET");
            c.setRequestProperty("Cache-Control", "no-cache");
            c.setRequestProperty("charset", "utf-8");
            c.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//            c.setRequestProperty("X-CSRF-Token", getToken(Utl.TokenURL));
            c.setRequestProperty("Cache-Control", "no-cache");
            c.setRequestProperty("Cache-Control", "no-store");
            c.setUseCaches(false);
            c.setDefaultUseCaches(false);
            c.setAllowUserInteraction(false);
            c.setConnectTimeout(timeout);
            c.setReadTimeout(timeout);

//            String token = new UserRepositoy(App.app.getApplicationContext()).getClient().getToken();
//            c.setRequestProperty("X-CSRF-Token", token);

            c.connect();
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
                    String hdr = c.getHeaderFields().toString();
                    Log.d("Aero", hdr);


                    return sb.toString();
                case 400:
                case 401:
                case 500:
                    BufferedReader br1 = new BufferedReader(new InputStreamReader(c.getErrorStream(), "UTF-8"));
                    StringBuilder sb1 = new StringBuilder();
                    String line1;
                    while ((line1 = br1.readLine()) != null) {
                        sb1.append(line1 + "\n");
                    }
                    br1.close();
                    String hdr1 = c.getHeaderFields().toString();
                    Log.d("Aero", hdr1);

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


    public static String flushNotification(String url, int timeout) {
        HttpURLConnection c = null;
        try {
            URL u = new URL(url);
            c = (HttpURLConnection) u.openConnection();
            c.setConnectTimeout(timeout);
            c.setReadTimeout(NetworkingHelper.RequestTimeout);
            c.setRequestMethod("DELETE");
            c.setRequestProperty("charset", "utf-8");
            c.setDoInput(true);
            c.setDoOutput(true);
            c.setUseCaches(false);
            c.setDefaultUseCaches(false);
            c.setRequestProperty("X-CSRF-Token", new UserRepositoy(App.app.getApplicationContext()).getClient().getToken());
//            for (HttpCookie cookie : JsonParser.cookies) {
//                c.setRequestProperty("Cookie", cookie.getValue());
//            }
            c.setRequestProperty("Content-Type", "application/json");

//            JSONObject jsonParam = new JSONObject();
//            for (Pair<String, String> pr : params) {
//                jsonParam.put(pr.first, pr.second);
//            }
//            DataOutputStream localDataOutputStream = new DataOutputStream(c.getOutputStream());
//            localDataOutputStream.writeBytes(jsonParam.toString());
//            localDataOutputStream.flush();
//            localDataOutputStream.close();

            c.connect();
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
//                case 401:
//                    BufferedReader br1 = new BufferedReader(new InputStreamReader(c.getErrorStream(), "UTF-8"));
//                    StringBuilder sb1 = new StringBuilder();
//                    String line1;
//                    while ((line1 = br1.readLine()) != null) {
//                        sb1.append(line1 + "\n");
//                    }
//                    br1.close();
//                    return sb1.toString();
            }
        } catch (MalformedURLException ex) {
            return NetworkingHelper.ErrorConnectionResponse;
        } catch (IOException ex) {
            return NetworkingHelper.ErrorConnectionResponse;
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
