package com.joyBox.shefaa.networking.connections;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by Adhamkh on 2018-10-05.
 */

public class GuardianshipConnections {

    public static String getJson(String url, int timeout) {
        HttpURLConnection c = null;
        try {
            // url = url.replace("%20", " ");
            //URL u = new URL(url);
//            String query = "";
//            if (u.getQuery() != null) {
//                query = URLEncoder.encode(u.getQuery(), "utf-8");
//
//            }
//            URI uri = new URI(u.getProtocol(), u.getUserInfo(), u.getHost(), u.getPort(), u.getPath(), u.getQuery(), u.getRef());
//            u = uri.toURL();
//            c = (HttpURLConnection) u.openConnection();
//            c.setDoOutput(true);
//            c.setDoInput(true);
//            c.setInstanceFollowRedirects(false);
//            c.setRequestMethod("GET");
//            c.setRequestProperty("Accept", "application/x-www-form-urlencoded;charset=utf-8");
//            c.setRequestProperty("content-type", "application/x-www-form-urlencoded;charset=utf-8");
            //        c.setRequestProperty("Content-Type", "text/plain; charset=utf-8");
//            c.setRequestProperty("charset", "utf-8");
//            c.setRequestProperty("Accept-Charset", "UTF-8");
            //  String cnt = u.getQuery().toString();
            //c.setRequestProperty("Content-Length", Integer.toString(u.getQuery().getBytes("utf-8").length));
//            c.setRequestProperty("Content-Length", "0");
//            c.setRequestProperty("Accept-Encoding", "identity");
            //c.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.155 Safari/537.36");
//            c.setRequestProperty("Accept-Encoding", "gzip, deflate");
            // c.setRequestProperty("content-encoding", );
//            String En = c.getContentEncoding();
//            String tp = c.getContentType();

//            c.setUseCaches(false);
//            c.setAllowUserInteraction(false);
//            c.setConnectTimeout(timeout);
//            c.setReadTimeout(timeout);
//            c.connect();
//            DataOutputStream wr = new DataOutputStream(c.getOutputStream());
//            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(wr, "UTF-8"));
//            writer.write(u.getQuery(), 0, u.getQuery().length());
//            writer.close();
//            wr.close();

            // url = url.replace("%20", " ");
            URL u = new URL(url);
           /*  String query = null;
            if (u.getQuery() != null) {
                query = URLEncoder.encode(u.getQuery(), "utf-8");
                query = query.replace("%3D", "=");
                query = query.replace("%26", "&");
            }
          URI uri = new URI(u.getProtocol(), u.getUserInfo(), u.getHost(), u.getPort(), u.getPath(), query, u.getRef());
            u = uri.toURL();*/
            //  url = u.getPath();
            c = (HttpURLConnection) u.openConnection();
            //c.setDoOutput(true);
            //c.setDoInput(true);
            CookieManager cookieManager = new CookieManager(null, CookiePolicy.ACCEPT_ALL);
            List<HttpCookie> httpCookieList = cookieManager.getCookieStore().getCookies();
//            for (HttpCookie cookie : cookies) {
//                String s = cookie.getValue();
//                //c.setRequestProperty("Cookie", cookie.getValue());
//                Log.v("cc", cookie.getValue());
//            }

            c.setInstanceFollowRedirects(false);

            c.setRequestMethod("GET");
            c.setRequestProperty("Cache-Control", "no-cache");
            //c.setRequestProperty("Accept", "*/*");
            // c.setRequestProperty("Accept-Encoding", "gzip");
            // c.setRequestProperty("Cookie", "SESSd027d45fa73fa119741a9e84ef80f829=ZLoaOU7nhP0I0keHwWhRHP-vtjkrZN2KTvwcFfnb7P0");
            //c.setRequestProperty("Accept-Language", "en-US,en,ar;q=0.8");
            //c.setRequestProperty("User-agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36");
            // c.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.1.2) Gecko/20090729 Firefox/3.5.2 (.NET CLR 3.5.30729)");
            c.setRequestProperty("charset", "utf-8");
            //c.setRequestProperty("Accept-Charset", "UTF-8");
            //c.setRequestProperty("Content-Length", "0");
            //c.setRequestProperty("User-Agent", "ZXing (Android)");
            // c.setRequestProperty("Accept", "*/*");
            c.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//            c.setRequestProperty("X-CSRF-Token", getToken(Utl.TokenURL));
            c.setRequestProperty("Cache-Control", "no-cache");
            c.setRequestProperty("Cache-Control", "no-store");
//            c.setRequestProperty("Connection", "Keep-Alive");
//            c.setRequestProperty("Keep-Alive", "header");
            c.setUseCaches(false);
            c.setDefaultUseCaches(false);
            c.setAllowUserInteraction(false);
            c.setConnectTimeout(timeout);
            c.setReadTimeout(timeout);
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
                case 500:
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
        } catch (MalformedURLException ex) {
            return "Error Connection";
        } catch (IOException ex) {
            return "Error Connection";
        } catch (Exception ex) {
            return "Error Connection";
        } finally {
            if (c != null) {
                try {
                    c.disconnect();

                } catch (Exception ex) {
                    return "Error Connection";
                }
            }
        }
        return "Error Connection";
    }

}
