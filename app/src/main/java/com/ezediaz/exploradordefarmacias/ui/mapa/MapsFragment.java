package com.ezediaz.exploradordefarmacias.ui.mapa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.ezediaz.exploradordefarmacias.R;
import com.ezediaz.exploradordefarmacias.databinding.FragmentMapsBinding;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class MapsFragment extends Fragment {
    private MapsFragmentViewModel vm;
    private FragmentMapsBinding binding;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap googleMap) {
            // Esta parte está comentada, puedes modificarla según tus necesidades
            // Agrega un marcador en Sydney, Australia (esto es solo un ejemplo)
            /*LatLng sydney = new LatLng(-34, 151);
            googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/
        }
    };

    // Método para crear la vista del fragmento
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Inicializa el ViewModel
        vm = new ViewModelProvider(this).get(MapsFragmentViewModel.class);

        // Inicializa el binding de la vista
        binding = FragmentMapsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Muestra un Toast indicando que se ha ejecutado el método
        Toast.makeText(getContext(), "onCreateView de MapsFragment ejecutado", Toast.LENGTH_SHORT).show();

        // Observa el estado del mapa actual y configura el mapa cuando está listo
        vm.getMMapaActual().observe(getViewLifecycleOwner(), mapaActual -> {
            SupportMapFragment smf = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapa);
            if (smf != null) {
                smf.getMapAsync(mapaActual);
            }
        });

        // Observa los cambios de ubicación y actualiza la ubicación en el ViewModel
        vm.getMLocation().observe(getViewLifecycleOwner(), location -> {
            vm.miUbicacion = new LatLng(location.getLatitude(), location.getLongitude());
            vm.obtenerMapa(); // Actualiza el mapa con la nueva ubicación
        });

        vm.lecturaPermanente();
        return root;
    }

    // Método llamado cuando la vista está completamente creada
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Obtiene el fragmento de mapa y asigna el callback
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapa);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }

    // Método llamado cuando se destruye la vista del fragmento
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Limpia el binding y el ViewModel
        binding = null;
        vm.pararLecturaPermanente();
    }
}
