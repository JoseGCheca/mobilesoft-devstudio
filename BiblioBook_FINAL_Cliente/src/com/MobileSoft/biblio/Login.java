package com.MobileSoft.biblio;

import java.util.concurrent.ExecutionException;

import com.MobileSoft.Comunicador.Comunicador;
import com.example.biblio.R;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnCancelListener;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity {
	ProgressDialog pDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		final EditText txtUser = (EditText) findViewById(R.id.LoginUser);
		final EditText txtPass = (EditText) findViewById(R.id.LoginPass);
		final Button btnEnter = (Button) findViewById(R.id.btnEnter);
		String usuario= "";
		 String pass= "";
		RelativeLayout RL01= (RelativeLayout) findViewById(R.id.login_rl01);
		RL01.setBackgroundColor(Color.LTGRAY);

		btnEnter.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Login.this, Principal.class);
				
				startActivity(intent);	
				/*String usuario= "";
				String pass= "";
				String result ="";
				pDialog = new ProgressDialog(Login.this);
				pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
				pDialog.setMessage("Procesando...");
				pDialog.setCancelable(true);
				pDialog.setMax(100);
				usuario= txtUser.getText().toString();
				pass= txtPass.getText().toString();
				
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
				
				if(result.equals("true")){
					Intent intent = new Intent(Login.this, Principal.class);
					
					startActivity(intent);	
					//pDialog.cancel();
					
				}
				else{
					Toast toast1 =
				            Toast.makeText(getApplicationContext(),
				                    "Usuario no registrado", Toast.LENGTH_SHORT);
				 
				        toast1.show();
				        //pDialog.cancel();
				}
			*/		
			}
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
			String a = null;
			String cadena="autenticar*:"+params[0];
			
			a=comunicador.Conectar(cadena);
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

		protected void onPostExecute() {
				pDialog.dismiss();
				//Toast.makeText(ConectarServer.this, "Tarea finalizada!",
						//Toast.LENGTH_SHORT).show();
			
		}

		@Override
		protected void onCancelled() {
			/*Toast.makeText(MainHilos.this, "Tarea cancelada!",
	            Toast.ENGTH_SHORT).show();*/
			pDialog.dismiss();
			
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	@Override
	public void onResume() {
		super.onResume();  // Always call the superclass method first
	}
	@Override
	public void onDestroy() {
		super.onDestroy();  // Always call the superclass method first
	}
}



















