package com.MobileSoft.biblio;

import com.example.biblio.R;
import com.example.biblio.R.layout;
import com.example.biblio.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Consultar_Libro extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_consultar_libro);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.consultar__libro, menu);
		return true;
	}

}
