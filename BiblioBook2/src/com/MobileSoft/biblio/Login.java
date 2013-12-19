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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class Login extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		final EditText txtUser = (EditText) findViewById(R.id.txtUser);
		final EditText txtPass = (EditText) findViewById(R.id.txtPass);
		final Button btnEnter = (Button) findViewById(R.id.btnEnter);
		
		RelativeLayout RL01= (RelativeLayout) findViewById(R.id.login_rl01);
		RL01.setBackgroundColor(Color.LTGRAY);
		
		btnEnter.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//Creamos el Intent
                Intent intent = new Intent(Login.this, Principal.class);

                //Creamos la información a pasar entre actividades
                Bundle b = new Bundle();
                b.putString("NOMBRE", txtUser.getText().toString());

                //Añadimos la informaci�n al intent
                intent.putExtras(b);

                //Iniciamos la nueva actividad
                startActivity(intent);
			}
		});
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

