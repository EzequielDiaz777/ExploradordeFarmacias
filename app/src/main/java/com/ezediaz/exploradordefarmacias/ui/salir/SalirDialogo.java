package com.ezediaz.exploradordefarmacias.ui.salir;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class SalirDialogo {
    public static void mostrarDialogo(Context context){
        new AlertDialog.Builder(context)
                .setTitle("Confirmar salida")
                .setMessage("¿Está seguro que desea salir?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(0);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }
}

