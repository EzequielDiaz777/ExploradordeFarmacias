package com.ezediaz.exploradordefarmacias.ui.salir;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.ezediaz.exploradordefarmacias.MainActivityViewModel;

import java.util.Objects;

public class SalirDialogo extends AndroidViewModel {
    private static MainActivityViewModel vm;
    public SalirDialogo(@NonNull Application application) {
        super(application);
    }
    public static void mostrarDialogo(Context context){
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance((Application) context.getApplicationContext()).create(MainActivityViewModel.class);
        if(Objects.requireNonNull(vm.getMIdioma().getValue()).equalsIgnoreCase("es")){
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
        } else {
            new AlertDialog.Builder(context)
                    .setTitle("Confirm exit")
                    .setMessage("Are you sure you want to exit?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
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
}

