package edu.empleartec.clase9;

import android.text.TextUtils;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

/**
 * Created by gcalero
 */
public class HttpClient {

    public static String doPost(URL url, String content) throws IOException {
//        public HttpResponse doPost(URL url, String contentType, Map<String, String> httpHeaders, byte[] content) throws IOException {
        HttpURLConnection urlConnection = null;
        int responseCode = -1;
        String respuesta = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            urlConnection.setChunkedStreamingMode(0);
            urlConnection.setRequestProperty("Content-Type", "application/json");
            OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());

            writeStream(out, content);
            responseCode = urlConnection.getResponseCode();

            if (urlConnection.getContentLength() > 0) {
                InputStream is = urlConnection.getInputStream();
                respuesta = readIt(is);
            }
            return respuesta;
        } finally {
            urlConnection.disconnect();
        }

    }

    public static String doGet(URL url) throws IOException {
        int responseCode = -1;

        String respuesta = null;
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoInput(true);
            // Configuro el request
            // urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.connect();
            responseCode = urlConnection.getResponseCode();
            InputStream is = urlConnection.getInputStream();
            // Convert the InputStream into a string
            respuesta = readIt(is);
            return respuesta;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }

    private static String readIt(InputStream is) {
        // TODO: leer los datos del response
        return "";
    }

    private static void writeStream(OutputStream out, String content) {
        // TODO: escribir el contenido a enviar

    }


}
