package com.ezediaz.exploradordefarmacias.ui.lista;

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
                String t = "";
                binding.tvNombre.setText(farmacia.getNombre());
                binding.ivFoto.setImageResource(farmacia.getFoto());
                t = binding.tvDireccion.getText().toString() + " " + farmacia.getDireccion();
                binding.tvDireccion.setText(t);
                t = binding.tvHorario.getText().toString() + " " + farmacia.getHorario();
                binding.tvHorario.setText(t);
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