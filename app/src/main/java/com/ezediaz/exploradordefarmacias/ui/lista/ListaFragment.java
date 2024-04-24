package com.ezediaz.exploradordefarmacias.ui.lista;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ezediaz.exploradordefarmacias.databinding.FragmentListaBinding;
import com.ezediaz.exploradordefarmacias.modelo.Farmacia;

import java.util.List;

public class ListaFragment extends Fragment {

    private FragmentListaBinding binding;
    private ListaFragmentViewModel vm;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentListaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(ListaFragmentViewModel.class);

        vm.getMFarmacias().observe(getViewLifecycleOwner(), new Observer<List<Farmacia>>() {
            @Override
            public void onChanged(List<Farmacia> farmacias) {
                FarmaciaAdapter farmaciaAdapter = new FarmaciaAdapter(farmacias, getLayoutInflater());
                GridLayoutManager glm = new GridLayoutManager(container.getContext(), 1, GridLayoutManager.VERTICAL, false);
                RecyclerView rv = binding.listadoDeFarmacias;
                rv.setLayoutManager(glm);
                rv.setAdapter(farmaciaAdapter);
            }
        });
        vm.cargarFarmacias();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
