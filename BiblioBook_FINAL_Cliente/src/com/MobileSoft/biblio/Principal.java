package com.MobileSoft.biblio;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutionException;

import com.MobileSoft.Comunicador.Comunicador;
import com.MobileSoft.biblio.Login.ConectarServer;
import com.example.biblio.R;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

public class Principal extends Activity {
	ProgressDialog pDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal);
		RelativeLayout RL01= (RelativeLayout) findViewById(R.id.principal_rl01);
		RL01.setBackgroundColor(Color.LTGRAY);
		Button btnDatos = (Button) findViewById(R.id.btnDatos);
		Button btnConsultar = (Button) findViewById(R.id.btnConsultar);
		Button btnModificar = (Button) findViewById(R.id.btnModificar);
		Button btnPrestamos = (Button) findViewById(R.id.btnPrestamos);
		//pDialog  = (ProgressBar) findViewById(R.id.progressBar2);  

		btnDatos.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Principal.this, Datos_personales.class);
				startActivity(intent);
				//Creamos el Intent
				/*String prueba ="";
				pDialog = new ProgressDialog(Principal.this);
				pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
				pDialog.setMessage("Procesando...");
				pDialog.setCancelable(true);
				pDialog.setMax(100);

				ConectarServer tarea2 = new ConectarServer();
				tarea2.execute("consultarMisDatos");
				try{

					prueba=tarea2.get();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				tarea2.isCancelled();
				Intent intent = new Intent(Principal.this, Datos_personales.class);
				//Creamos la información a pasar entre actividades
				Bundle b = new Bundle();
				b.putString("NOMBRE", prueba);
				intent.putExtras(b);
				//Añadimos la informaci�n al intent
				// intent.putExtras(b);
				//Iniciamos la nueva actividad
				startActivity(intent);
			*/}
		});
		btnConsultar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//new ConectarServer().execute();


				Intent intent = new Intent(Principal.this, Consultar_Libro.class);
				//Creamos la información a pasar entre actividades
				// Bundle b = new Bundle();
				//  b.putString("NOMBRE",prueba );
				//Añadimos la informaci�n al intent
				// intent.putExtras(b);
				//Iniciamos la nueva actividad
				startActivity(intent);

			}
		});
		btnModificar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//Creamos el Intent
				Intent intent = new Intent(Principal.this, Modificar_Datos.class);
				//Creamos la información a pasar entre actividades
				// Bundle b = new Bundle();
				// b.putString("NOMBRE", txtUser.getText().toString());
				//Añadimos la informaci�n al intent
				// intent.putExtras(b);
				//Iniciamos la nueva actividad
				startActivity(intent);
			}
		});
		btnPrestamos.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Principal.this, Mis_prestamos.class);
				startActivity(intent);
				/*String usuario= "";
				String pass= "";
				String result ="";
				pDialog = new ProgressDialog(Principal.this);
				pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
				pDialog.setMessage("Procesando...");
				pDialog.setCancelable(true);
				pDialog.setMax(100);
				//usuario= txtUser.getText().toString();
				//pass= txtPass.getText().toString();
				
				ConectarServer tarea2 = new ConectarServer();
				tarea2.execute(pass+":"+usuario);
				try{

					result=tarea2.get();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				tarea2.isCancelled();
			*/}
		});
	}
	class ConectarServer extends AsyncTask<String, Integer, String>{
		// Do the long-running work in here
		/*protected Long doInBackground( opcion) {
	        /*int count = urls.length;
	        long totalSize = 0;
	        for (int i = 0; i < count; i++) {
	            totalSize += Downloader.downloadFile(urls[i]);
	            publishProgress((int) ((i / (float) count) * 100));
	            // Escape early if cancel() is called
	            if (isCancelled()) break;
	        }
	        return totalSize;*/




		// This is called when doInBackground() is finished
		protected void onPostExecute(Long result) {
			//showNotification("Downloaded " + result + " bytes");
		}

		@Override
		protected String doInBackground(String... params) {
			Comunicador comunicador  = new Comunicador();
			//publishProgress();
			String a=null;
			a= comunicador.Conectar(params[0]);
			if(isCancelled()){

			}
			return a;
		}
		@Override
		protected void onProgressUpdate(Integer... values) {
			int progreso = values[0].intValue();

			pDialog.setProgress(progreso);
		}

		@Override
		protected void onPreExecute() {

			pDialog.setOnCancelListener(new OnCancelListener() {
				public void onCancel(DialogInterface dialog) {
					ConectarServer.this.cancel(true);
				}
			});

			pDialog.setProgress(0);
			pDialog.show();
		}

		protected void onPostExecute(Boolean result) {
			if(result)
			{
				pDialog.dismiss();
				//Toast.makeText(ConectarServer.this, "Tarea finalizada!",
						//Toast.LENGTH_SHORT).show();
			}
		}

		@Override
		protected void onCancelled() {
			/*Toast.makeText(MainHilos.this, "Tarea cancelada!",
	            Toast.LENGTH_SHORT).show();*/
		}


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.principal, menu);
		return true;
	}
}












