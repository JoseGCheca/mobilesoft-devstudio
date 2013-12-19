package com.MobileSoft.biblio;

import com.example.biblio.R;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

public class Datos_personales extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_datos_personales);
		
		ScrollView SV01= (ScrollView) findViewById(R.id.SV01);
		SV01.setBackgroundColor(Color.LTGRAY);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.datos_personales, menu);
		return true;
	}

}
