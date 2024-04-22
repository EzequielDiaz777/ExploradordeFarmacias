package com.ezediaz.exploradordefarmacias.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.ezediaz.exploradordefarmacias.databinding.ActivityFarmaciaBinding;
import com.ezediaz.exploradordefarmacias.modelo.Farmacia;

public class FarmaciaActivity extends AppCompatActivity {
    private ActivityFarmaciaBinding binding;
    private FarmaciaActivityViewModel mv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFarmaciaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(FarmaciaActivityViewModel.class);
        mv.getMutableFarmacia().observe(this, new Observer<Farmacia>(){
            @Override
            public void onChanged(Farmacia farmacia) {
                Log.d("salida", "Estoy en onChanged");
                binding.tvNombre.setText(farmacia.getNombre());
                binding.ivFoto.setImageResource(farmacia.getFoto());
                binding.tvDireccion.setText("Dirección de la farmacia: " + farmacia.getDireccion());
                binding.tvHorario.setText("Horario de atención: " + farmacia.getHorario());
                binding.btnVolver.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBackPressed();
                    }
                });
            }
        });
        mv.cargarFarmacia(getIntent());
    }
}