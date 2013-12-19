package com.MobileSoft.biblio;

import com.example.biblio.R;
import com.example.biblio.R.layout;
import com.example.biblio.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Modificar_Datos extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modificar_datos);
		
		Spinner cmbOpciones;
		
		cmbOpciones = (Spinner)findViewById(R.id.spinner);
		ArrayAdapter<CharSequence> adapter =
			    ArrayAdapter.createFromResource(this,
			        R.array.valores_array,
			        android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(
		        android.R.layout.simple_spinner_dropdown_item);
		 
		cmbOpciones.setAdapter(adapter);
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.modificar__datos, menu);
		return true;
	}

}
