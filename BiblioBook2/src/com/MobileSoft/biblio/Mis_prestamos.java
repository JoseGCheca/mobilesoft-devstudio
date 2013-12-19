package com.MobileSoft.biblio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.biblio.R;

import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

@SuppressLint("NewApi")
public class Mis_prestamos extends Activity {
	
	/*@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	@SuppressLint("NewApi")*/
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mis_prestamos);
		
		LinearLayout LL01= (LinearLayout) findViewById(R.id.Mis_prestamosLL_01);
		LL01.setBackgroundColor(Color.LTGRAY);

		final ListView listview = (ListView) findViewById(R.id.ListView1);
		String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
				"Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
				"Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
				"OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
				"Android", "iPhone", "WindowsMobile" };

		final ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < values.length; ++i) {
			list.add(values[i]);
		}
		final StableArrayAdapter adapter = new StableArrayAdapter(this,
				android.R.layout.simple_list_item_1, list);
		listview.setAdapter(adapter);

		/*listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
			@SuppressLint("NewApi")
			@Override
			public void onItemClick(AdapterView<?> parent, final View view,
					int position, long id) {
				final String item = (String) parent.getItemAtPosition(position);
				view.animate().setDuration(2000).alpha(0).withEndAction(new Runnable() {
					@Override
					public void run() {
						list.remove(item);
						adapter.notifyDataSetChanged();
						view.setAlpha(1);
					}
				});
			}

		});*/
		
		/*listview.setOnItemLongClickListener(new OnItemLongClickListener() {

			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int pos, long id) {
				// TODO Auto-generated method stub

				Log.v("long clicked","pos: " + pos);
				
				return true;
			}
		}); */
		listview.setOnItemLongClickListener(new OnItemLongClickListener() {

		      @Override
		      public boolean onItemLongClick(AdapterView<?> parent, View view,
		          int position, long id) {
		        Toast.makeText(Mis_prestamos.this,
		            "Item in position " + position + " clicked",
		            Toast.LENGTH_LONG).show();
		        	FragmentManager fragmentManager = getFragmentManager();
		        	DialogoInfoPrestamos dialogo = new DialogoInfoPrestamos();
		        	dialogo.show(fragmentManager, "tagAlerta");
		       
		        // Return true to consume the click event. In this case the
		        // onListItemClick listener is not called anymore.
		        return true;
		      }
		    });
		
		
		
		/*listView.setOnItemClickListener(new OnItemClickListener() {
			  @Override
			  public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
			    Toast.makeText(getApplicationContext(),"Click ListItem Number " + position, Toast.LENGTH_LONG).show();
			  }
			});*/  
		/*btnEnter.setOnClickListener(new OnClickListener() {
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
		});*/

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mis_prestamos, menu);
		return true;
	}
	private class StableArrayAdapter extends ArrayAdapter<String> {

		HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

		public StableArrayAdapter(Context context, int textViewResourceId,
				List<String> objects) {
			super(context, textViewResourceId, objects);
			for (int i = 0; i < objects.size(); ++i) {
				mIdMap.put(objects.get(i), i);
			}
		}
		@Override
		public long getItemId(int position) {
			String item = getItem(position);
			return mIdMap.get(item);
		}

		@Override
		public boolean hasStableIds() {
			return true;
		}

	}
}
