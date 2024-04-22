package com.ezediaz.exploradordefarmacias.ui.mapa;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.ezediaz.exploradordefarmacias.R;
import com.ezediaz.exploradordefarmacias.databinding.FragmentMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsFragment extends Fragment {
    private MapsFragmentViewModel vm;
    private FragmentMapsBinding binding;
    private GoogleMap map;
    private OnMapReadyCallback callback = new OnMapReadyCallback() {
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            map = googleMap;
            /*LatLng sydney = new LatLng(-34, 151);
            googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/
        }
    };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        vm = new ViewModelProvider(this).get(MapsFragmentViewModel.class);
        binding = FragmentMapsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        vm.getMMapaActual().observe(getViewLifecycleOwner(), new Observer<MapsFragmentViewModel.MapaActual>() {
            @Override
            public void onChanged(MapsFragmentViewModel.MapaActual mapaActual) {
                SupportMapFragment smf = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapa);
                smf.getMapAsync(mapaActual);
            }
        });
        vm.getMLocation().observe(getViewLifecycleOwner(), new Observer<Location>() {
            @Override
            public void onChanged(Location location) {
                vm.miUbicacion = new LatLng(location.getLatitude(), location.getLongitude());
                vm.obtenerMapa();
            }
        });
        vm.obtenerUltimaUbicacion();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapa);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}