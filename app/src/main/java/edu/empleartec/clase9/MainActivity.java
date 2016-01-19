package edu.empleartec.clase9;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Pair;
import android.widget.Toast;

public class MainActivity extends Activity implements GeneradorDeMultiplosAsyncTask.IMultiplosCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new GeneradorDeMultiplosAsyncTask(this).execute(new Pair<Integer, Integer>(10, 100));

        UnIntentService.startActionFoo(this, "Hola", "Mundo");
    }

    private ProgressDialog dialog;
    @Override
    public void prepareUI() {
        dialog = new ProgressDialog(this);
        dialog.setMessage(getString(R.string.calculando_multiplos));
        dialog.show();
    }

    @Override
    public void taskFinished(Long sum) {
        if (dialog != null) {
            dialog.hide();
        }
        Toast.makeText(this, "Sum = " + sum, Toast.LENGTH_LONG).show();
    }
}
