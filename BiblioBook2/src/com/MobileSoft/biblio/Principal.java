package com.MobileSoft.biblio;

import com.example.biblio.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;

public class Principal extends Activity {

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
		
		
		btnDatos.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//Creamos el Intent
                Intent intent = new Intent(Principal.this, Datos_personales.class);
                //Creamos la información a pasar entre actividades
               // Bundle b = new Bundle();
               // b.putString("NOMBRE", txtUser.getText().toString());
                //Añadimos la informaci�n al intent
               // intent.putExtras(b);
                //Iniciamos la nueva actividad
                startActivity(intent);
			}
		});
		btnConsultar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//new ConectarServer().execute();
				
				
				Intent intent = new Intent(Principal.this, Consultar_Libro.class);
                //Creamos la información a pasar entre actividades
               // Bundle b = new Bundle();
               // b.putString("NOMBRE", txtUser.getText().toString());
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
				//Creamos el Intent
                Intent intent = new Intent(Principal.this, Mis_prestamos.class);
                //Creamos la información a pasar entre actividades
               // Bundle b = new Bundle();
                
                
               // b.putString("NOMBRE", txtUser.getText().toString());
                //Añadimos la informaci�n al intent
               // intent.putExtras(b);
                //Iniciamos la nueva actividad
                startActivity(intent);
			}
		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.principal, menu);
		return true;
	}
}
