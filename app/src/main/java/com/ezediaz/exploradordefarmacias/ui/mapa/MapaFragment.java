package com.ezediaz.exploradordefarmacias.ui.mapa;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.ezediaz.exploradordefarmacias.MainActivityViewModel;
import com.ezediaz.exploradordefarmacias.R;
import com.ezediaz.exploradordefarmacias.databinding.FragmentMapaBinding;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
public class MapaFragment extends Fragment {
    private MapaFragmentViewModel viewModelMapa;
    private MainActivityViewModel viewModelMA;
    private FragmentMapaBinding binding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModelMapa = new ViewModelProvider(this).get(MapaFragmentViewModel.class);
        viewModelMA = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);
        binding = FragmentMapaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        viewModelMapa.lecturaPermanente();
        observeViewModelChanges();
        return root;
    }
    private void observeViewModelChanges() {
        viewModelMapa.getMLocation().observe(getViewLifecycleOwner(), location -> {
            viewModelMapa.miUbicacion = new LatLng(location.getLatitude(), location.getLongitude());
            viewModelMapa.actualizarMapa();
        });
        viewModelMapa.getMMapaActual().observe(getViewLifecycleOwner(), mapaActual -> {
            SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapa);
            if (mapFragment != null) {
                mapFragment.getMapAsync(mapaActual);
                viewModelMA.getTipoDeMapa().observe(getViewLifecycleOwner(), new Observer<String>() {
                    @Override
                    public void onChanged(String tipoDeMapa) {
                        viewModelMapa.obtenerTipoDeMapa(tipoDeMapa);
                    }
                });
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        viewModelMapa.pararLecturaPermanente();
        binding = null;
    }
}