package zomwi.ejemploasynctask;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends Activity {

	private Button boton;
	private ProgressBar progressBar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		boton = (Button) findViewById(R.id.boton);
		progressBar = (ProgressBar) findViewById(R.id.progressBar);
	}

	public void iniciar(View view) {
		new Cargar().execute();
	}

	public class Cargar extends AsyncTask<Void, Integer, Void> {

		private int progreso;

		@Override
		protected Void doInBackground(Void... arg0) {
			while (progreso < 100) {
				progreso++;
				publishProgress(progreso);
				SystemClock.sleep(100);
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			boton.setClickable(true);
		}

		@Override
		protected void onPreExecute() {
			progreso = 0;
			boton.setClickable(false);
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			progressBar.setProgress(values[0]);
		}
	}
}