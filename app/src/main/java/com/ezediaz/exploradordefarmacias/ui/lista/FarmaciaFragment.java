package com.ezediaz.exploradordefarmacias.ui.lista;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.ezediaz.exploradordefarmacias.databinding.FragmentFarmaciaBinding;
import com.ezediaz.exploradordefarmacias.modelo.Farmacia;

public class FarmaciaFragment extends Fragment {
    private FragmentFarmaciaBinding binding;
    private FarmaciaFragmentViewModel vm;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFarmaciaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        vm = new ViewModelProvider(this).get(FarmaciaFragmentViewModel.class);
        vm.getMFarmacia().observe(getViewLifecycleOwner(), new Observer<Farmacia>() {
            String t;
            @Override
            public void onChanged(Farmacia farmacia) {
                binding.tvNombre.setText(farmacia.getNombre());
                binding.ivFoto.setImageResource(farmacia.getFoto());
                t = binding.tvDireccion.getText().toString() + " " + farmacia.getDireccion();
                binding.tvDireccion.setText(t);
                t = binding.tvHorario.getText().toString() + " " + farmacia.getHorario();
                binding.tvHorario.setText(t);
            }
        });
        vm.cargarFarmacia(getArguments());
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
