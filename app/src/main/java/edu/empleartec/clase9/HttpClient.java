package edu.empleartec.clase9;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

/**
 * Created by gcalero
 */
public class HttpClient {

    private static final int BUFFER_LENGTH = 4096;
    private static final String UTF8 = "UTF-8";
    private static final String TAG = "HttpClient";

    public static String doPost(URL url, String content) throws IOException {
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
        Log.d(TAG, "Do GET");

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
            Log.d(TAG, "Response code " + responseCode);
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

    private static String readIt(InputStream stream) throws IOException {
        if (stream == null) return "";
        int readCount = 0;
        Reader reader = null;
        reader = new InputStreamReader(stream, UTF8);
        char[] buffer = new char[BUFFER_LENGTH];
        StringWriter writer = new StringWriter();
        //readCount is important, as due to this buffer for BUFFER_LENGTH was creating Non-Empty String for Empty Response.
        while (-1 != (readCount = reader.read(buffer))) {
            writer.write(buffer, 0, readCount);
        }
        return writer.toString();
    }

    private static void writeStream(OutputStream out, String content) {
        // TODO: escribir el contenido a enviar

    }

    // {"coord":{"lon":-57.95,"lat":-34.92},
    // "weather":[{"id":802,"main":"Clouds","description":"scattered clouds","icon":"03d"}],"base":"cmc stations","main":{"temp":299.15,"pressure":1016,"humidity":39,"temp_min":299.15,"temp_max":299.15},"wind":{"speed":5.7,"deg":60},"clouds":{"all":40},"dt":1453212000,"sys":{"type":3,"id":4699,"message":0.0025,"country":"AR","sunrise":1453193892,"sunset":1453244794},"id":3432043,"name":"La Plata","cod":200}
    public JSONObject parse (String jsonAsString) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonAsString);
        JSONObject coord = jsonObject.optJSONObject("coord");
        if (coord != null) {
            double lon = coord.optDouble("lon");

        }
        JSONArray weathers = jsonObject.optJSONArray("weather");
        int length = weathers.length();
        for (int i=0; i < length; i++) {
            JSONObject aWheather = weathers.getJSONObject(i);

        }
        return jsonObject;
    }

    public JSONObject buildObject (Long id, String name, String description) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", id);
            jsonObject.put("main", name);
            jsonObject.put("description", description);
            // jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

}
