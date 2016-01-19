package edu.empleartec.clase9;

import android.os.AsyncTask;
import android.util.Pair;

/**
 * Created by gcalero
 * Recibe un par <A,B> donde B es la cantidad de múltiplos a generar de A
 * El resultado es la suma de todos los múltiplos generados
 */
public class GeneradorDeMultiplosAsyncTask extends AsyncTask <Pair<Integer, Integer>, Void, Long > {


    private final IMultiplosCallback callback;

    public interface IMultiplosCallback {
        void prepareUI();
        void taskFinished(Long sum);
    }

    public GeneradorDeMultiplosAsyncTask(IMultiplosCallback callback) {
        this.callback = callback;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (callback != null) {
            callback.prepareUI();
        }
    }

    @Override
    protected Long doInBackground(Pair<Integer, Integer>... pairs) {
        long sum = 0;
        for (int i=0; i < pairs.length; i++) {
            Integer A = pairs[i].first;
            Integer B = pairs[i].second;
            for (int x = 1; x <= B; x++) {
                int multiplo = x * A;
                sum += multiplo;
                // agregamos demora solo con fines demostrativos
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return sum;
    }

    @Override
    protected void onPostExecute(Long sum) {
        super.onPostExecute(sum);
        if (callback != null) {
            callback.taskFinished(sum);
        }
    }
}
