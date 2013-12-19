package com.MobileSoft.biblio;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class DialogoInfoLibro extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
 
        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());
 
        builder.setMessage("Esto es una prueba deja de cansinear hermoso¡¡¡\n"+"hola joakiner\n"+"jaejeje")
               .setTitle("Información")
               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       dialog.cancel();
                   }
               });
 
        return builder.create();
    }
}