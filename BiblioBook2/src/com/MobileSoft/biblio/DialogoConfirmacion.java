package com.MobileSoft.biblio;

import com.example.biblio.R;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
@SuppressLint("NewApi")
public class DialogoConfirmacion extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
    	
    	
    	Bundle bundle = this.getArguments();
    	final String opcion = bundle.getString("Opcion");
    	
        AlertDialog.Builder builder =new AlertDialog.Builder(getActivity());
 
        builder.setMessage("¿Confirma la acción seleccionada?")
        .setTitle("Confirmacion")
        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener()  {
               public void onClick(DialogInterface dialog, int id) {
                    Log.i("Dialogos", "Confirmacion Aceptada.");
                    if(opcion.equals("Renovar")){
                    	Toast toast1 =
                                Toast.makeText(getActivity(), "Usted ha renovado su libro", Toast.LENGTH_SHORT);
                            toast1.show();
                    	
                    }
                    else
                    {
                    	Toast toast1 =
                                Toast.makeText(getActivity(), "Usted ha devuelto su libro", Toast.LENGTH_SHORT);
                            toast1.show();
                    }
                        dialog.cancel();
                   }
               })
        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int id) {
                        Log.i("Dialogos", "Confirmacion Cancelada.");
                        if(opcion.equals("Devolver")){
                        	Toast toast1 =
                                    Toast.makeText(getActivity(), "Usted ha cancelado la renovación", Toast.LENGTH_SHORT);
                                toast1.show();
                        	
                        }
                        else
                        {
                        	Toast toast1 =
                                    Toast.makeText(getActivity(), "Usted ha cancelado la devolución", Toast.LENGTH_SHORT);
                                toast1.show();
                        }
                        dialog.cancel();
                        
                   }
               });
 
        return builder.create();
    }
}