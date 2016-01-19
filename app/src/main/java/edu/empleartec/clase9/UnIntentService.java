package edu.empleartec.clase9;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class UnIntentService extends IntentService {

    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_DO_SOMETHING = "edu.empleartec.clase9.action.HacerAlgo";

    // El servicio puede recibir parámetros
    private static final String EXTRA_PARAM1 = "edu.empleartec.clase9.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "edu.empleartec.clase9.extra.PARAM2";
    private static final String TAG = "Service";

    public UnIntentService() {
        super("UnIntentService");
    }

    /**
     * Helper method (static). Los crea Android Studio (opcional)
     */
    public static void startActionFoo(Context context, String param1, String param2) {
        /* Esta es la verdadera invocación al servicio */
        //Intent intent = new Intent(context, UnIntentService.class);
        Intent intent = new Intent("edu.empleartec.clase9.MyService");
        //intent.setAction(ACTION_DO_SOMETHING);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        // Inicio el servicio
        context.startService(intent);
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            //if (ACTION_DO_SOMETHING.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
            handleActionDoSomething(param1, param2);
            //}
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionDoSomething(String param1, String param2) {
        URL url = null;
        try {
            url = new URL("http://api.openweathermap.org/data/2.5/weather?q=Buenos%20Aires,uk&appid=2de143494c0b295cca9337e1e96b00e0");
            String response = HttpClient.doGet(url);
            Log.wtf(TAG, "Response " + response);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
