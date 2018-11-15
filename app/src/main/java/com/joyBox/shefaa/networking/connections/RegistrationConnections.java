package com.joyBox.shefaa.networking.connections;

import android.util.Log;
import android.util.Pair;

import com.joyBox.shefaa.App;
import com.joyBox.shefaa.entities.Client;
import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.repositories.UserRepository;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by Adhamkh on 2018-08-10.
 */

public class RegistrationConnections {

    public static List<HttpCookie> cookies;

    public static String getJsonSignIn(String url, List<Pair<String, String>> params, int timeout) {

        HttpURLConnection c = null;
        try {
            CookieManager cm = new CookieManager(null, CookiePolicy.ACCEPT_ALL);
            CookieHandler.setDefault(cm);
            boolean b = cm.getCookieStore().removeAll();
            URL u = new URL(url);
            c = (HttpURLConnection) u.openConnection();
            c.addRequestProperty("Expires", "-1");
            c.setConnectTimeout(timeout);
            c.setReadTimeout(NetworkingHelper.RequestTimeout);
            c.setRequestMethod("POST");
            c.setRequestProperty("charset", "utf-8");
            c.setDoInput(true);
            c.setDoOutput(true);
            c.setUseCaches(false);
            c.setDefaultUseCaches(false);
            c.setRequestProperty("Content-Type", "application/json");
            c.setRequestProperty("Cache-Control", "no-cache");
            c.setRequestProperty("Cache-Control", "no-store");

            JSONObject jsonParam = new JSONObject();
            for (Pair<String, String> pr : params) {
                jsonParam.put(pr.first, pr.second);
            }

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(c.getOutputStream(), "UTF-8");
            outputStreamWriter.write(jsonParam.toString());
            outputStreamWriter.flush();
            outputStreamWriter.close();

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

                    cookies = cm.getCookieStore().getCookies();

                    for (HttpCookie cookie : cookies) {
                        new UserRepository(App.app).putCookei(cookie.toString());
                    }
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

                    cookies = cm.getCookieStore().getCookies();
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

    public static String getForgotPassword(String url, List<Pair<String, String>> params, int timeout) {
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
                case 400:
                    BufferedReader br1 = new BufferedReader(new InputStreamReader(c.getErrorStream(), "UTF-8"));
                    StringBuilder sb1 = new StringBuilder();
                    String line1;
                    while ((line1 = br1.readLine()) != null) {
                        sb1.append(line1 + "\n");
                    }
                    br1.close();
                    return sb1.toString();

                case 406:
                    return NetworkingHelper.EmailNotFoundResponse;
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

    public static String putJsonChangeEmail(String url, List<Pair<String, String>> params, int timeout) {

        HttpURLConnection c = null;
        try {
            CookieManager cm = new CookieManager(null, CookiePolicy.ACCEPT_ALL);
            CookieHandler.setDefault(cm);
            boolean b = cm.getCookieStore().removeAll();
            URL u = new URL(url);
            c = (HttpURLConnection) u.openConnection();
            c.addRequestProperty("Expires", "-1");
            c.setConnectTimeout(timeout);
            c.setReadTimeout(NetworkingHelper.RequestTimeout);
            c.setRequestMethod("PUT");
            c.setRequestProperty("charset", "utf-8");
            c.setDoInput(true);
            c.setDoOutput(true);
            c.setUseCaches(false);
            c.setDefaultUseCaches(false);
            c.setRequestProperty("Content-Type", "application/json");
            c.setRequestProperty("Cache-Control", "no-cache");
            c.setRequestProperty("Cache-Control", "no-store");

            Client client = new UserRepository(App.app.getApplicationContext()).getClient();
            c.setRequestProperty("X-CSRF-Token", client.getToken());
            c.setRequestProperty("sessid", client.getSessid());
            c.setRequestProperty("session_name", client.getSessionName());

            JSONObject jsonParam = new JSONObject();
            for (Pair<String, String> pr : params) {
                jsonParam.put(pr.first, pr.second);
            }

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(c.getOutputStream(), "UTF-8");
            outputStreamWriter.write(jsonParam.toString());
            outputStreamWriter.flush();
            outputStreamWriter.close();

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

                    cookies = cm.getCookieStore().getCookies();

                    for (HttpCookie cookie : cookies) {
                        new UserRepository(App.app).putCookei(cookie.toString());
                    }
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

                    cookies = cm.getCookieStore().getCookies();
//                    return sb1.toString();
                    return NetworkingHelper.ErrorConnectionResponse;
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

    public static String putJsonChangePassword(String url, List<Pair<String, String>> params, int timeout) {

        HttpURLConnection c = null;
        try {
            CookieManager cm = new CookieManager(null, CookiePolicy.ACCEPT_ALL);
            CookieHandler.setDefault(cm);
            boolean b = cm.getCookieStore().removeAll();
            URL u = new URL(url);
            c = (HttpURLConnection) u.openConnection();
            c.addRequestProperty("Expires", "-1");
            c.setConnectTimeout(timeout);
            c.setReadTimeout(NetworkingHelper.RequestTimeout);
            c.setRequestMethod("PUT");
            c.setRequestProperty("charset", "utf-8");
            c.setDoInput(true);
            c.setDoOutput(true);
            c.setUseCaches(false);
            c.setDefaultUseCaches(false);
            c.setRequestProperty("Content-Type", "application/json");
            c.setRequestProperty("Cache-Control", "no-cache");
            c.setRequestProperty("Cache-Control", "no-store");

            Client client = new UserRepository(App.app.getApplicationContext()).getClient();
            c.setRequestProperty("X-CSRF-Token", client.getToken());
            c.setRequestProperty("sessid", client.getSessid());
            c.setRequestProperty("session_name", client.getSessionName());

            JSONObject jsonParam = new JSONObject();
            for (Pair<String, String> pr : params) {
                jsonParam.put(pr.first, pr.second);
            }

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(c.getOutputStream(), "UTF-8");
            outputStreamWriter.write(jsonParam.toString());
            outputStreamWriter.flush();
            outputStreamWriter.close();

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

                    cookies = cm.getCookieStore().getCookies();

                    for (HttpCookie cookie : cookies) {
                        new UserRepository(App.app).putCookei(cookie.toString());
                    }
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

                    cookies = cm.getCookieStore().getCookies();
//                    return sb1.toString();
                    return NetworkingHelper.ErrorConnectionResponse;
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

    public static String postJsonLogout(String url , int timeout) {
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
            Client client = new UserRepository(App.app.getApplicationContext()).getClient();
            c.setRequestProperty("X-CSRF-Token", client.getToken());
            c.setRequestProperty("sessid", client.getSessid());
            c.setRequestProperty("session_name", client.getSessionName());

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
                case 400:
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
