package com.MobileSoft.biblio;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;



@TargetApi(Build.VERSION_CODES.HONEYCOMB)
@SuppressLint("NewApi")
public class DialogoInfoPrestamos extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
 
     final String[] items = {"Info préstamo", "Renovar", "Devolver"};
 
        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());
 
        builder.setTitle("Selección").setItems(items, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				FragmentManager manager = getFragmentManager();
				//ndroid.support.v4.app.FragmentManager manager2 =getSupportFragmentManager();
		    	switch (which)
		    	{
		    	case 0:
		    		DialogoInfoLibro conf0 = new DialogoInfoLibro();
		    		
		    		Bundle b0 = new Bundle();
	                b0.putString("Opcion","Renovar");
		    		conf0.setArguments(b0);
		    		conf0.show(manager, "conf"); //.show(manager, "");//show(manager, "conf");
		    		break;
		    	case 1:
		    		DialogoConfirmacion conf1=new DialogoConfirmacion();
		    		Bundle b1 = new Bundle();
	                b1.putString("Opcion","Renovar");
		    		conf1.setArguments(b1);
		    		conf1.show(manager, "conf");
		    		break;
		    	case 2:
		    		DialogoConfirmacion conf2=new DialogoConfirmacion();
		    		Bundle b2 = new Bundle();
	                b2.putString("Opcion","Devolver");
		    		conf2.setArguments(b2);
		    		conf2.show(manager, "conf");
		    		break;
		  
		    	}
				
			}
               /* public void onClick(DialogInterface dialog, int item) {
                    Log.i("Dialogos", "Opción elegida: " + items[item]);
                    ListView lw = ((AlertDialog)dialog).getListView();
                    //int selectedPosition = ((AlertDialog)dialog).getListView().getCheckedItemPosition();
                    // ((AlertDialog)dialog).getListView().getCheckedItemPosition(); 
                     int a=((AlertDialog)dialog).getListView().getSelectedItemPosition();
                    DialogoInfoPrestamos obj = new DialogoInfoPrestamos();
                    obj.llamarDialogo(a);*/
            });
        
        
        return builder.create();
        };       
}
