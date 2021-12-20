package com.example.weather_service.Util;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class LoadWebSite {
    private HttpsURLConnection connection;
    private BufferedReader reader;
    private InputStream stream;
    private StringBuilder htmlCode;

    public LoadWebSite(String path){
        try {
            URL url = new URL(path);
            connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5000);
            connection.connect();
            stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
            htmlCode = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null){
                htmlCode.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(stream != null){
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                connection.disconnect();
            }
        }
    }

    public HttpsURLConnection getConnection() {
        return connection;
    }

    public InputStream getStream() {
        return stream;
    }

    public BufferedReader getReader() {
        return reader;
    }

    public String getHtmlCode() {
        return htmlCode.toString();
    }

}
