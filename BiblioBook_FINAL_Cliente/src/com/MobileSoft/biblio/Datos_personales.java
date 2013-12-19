package com.MobileSoft.biblio;

import com.example.biblio.R;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class Datos_personales extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_datos_personales);
		
		//find ids
		TextView txtDNI = (TextView)findViewById(R.id.Datos_DNI);
		TextView txtNombre = (TextView)findViewById(R.id.Datos_Nombre);
		TextView txtApellidos = (TextView)findViewById(R.id.Datos_Apellidos);
		TextView txtNacimiento = (TextView)findViewById(R.id.Datos_Nacimiento);
		TextView txtTelefono = (TextView)findViewById(R.id.Datos_Telefono);
		TextView txtPenalizado = (TextView)findViewById(R.id.Datos_Penalizado);
		TextView txtDireccion = (TextView)findViewById(R.id.Datos_Direccion);
		
		
		ScrollView SV01= (ScrollView) findViewById(R.id.SV01);
		SV01.setBackgroundColor(Color.LTGRAY);
		
		//Bundle bundle = this.getIntent().getExtras();
		
		//splits de cadena
		/*String s=bundle.getString("NOMBRE");
		System.out.println(s);
		String cadena=":"; /***para separar los distintos prestamos****/
		//String[] palabra=s.split(cadena);

		/*
		//setters
		txtDNI.setText(palabra[1]);
		txtNombre.setText(palabra[3]);
		txtApellidos.setText(palabra[4]+palabra[5]);
		txtNacimiento.setText(palabra[6]);
		txtTelefono.setText(palabra[7]);
		txtPenalizado.setText(palabra[8]);
		txtDireccion.setText(palabra[9]);*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.datos_personales, menu);
		return true;
	}

}
