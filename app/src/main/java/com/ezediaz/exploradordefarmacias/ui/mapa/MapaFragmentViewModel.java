package com.ezediaz.exploradordefarmacias.ui.mapa;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaFragmentViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Location> mLocation;
    private MutableLiveData<MapaActual> mMapaActual;
    private final FusedLocationProviderClient fusedLocationClient;
    private LocationCallback callback;
    public LatLng miUbicacion;
    private GoogleMap mapa;
    private Marker miMarca;
    private boolean isCamaraMovida = false;

    // Constructor
    public MapaFragmentViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
    }

    public LiveData<Location> getMLocation() {
        if(mLocation == null){
            mLocation = new MutableLiveData<>();
        }
        return mLocation;
    }

    public LiveData<MapaActual> getMMapaActual() {
        if(mMapaActual==null){
            mMapaActual = new MutableLiveData<>();
        }
        return mMapaActual;
    }

    public void lecturaPermanente() {
        LocationRequest locationRequest = new LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY,5000).build();
        callback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                Location lastLocation = locationResult.getLastLocation();
                mLocation.postValue(lastLocation);
            }
        };

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
            fusedLocationClient.requestLocationUpdates(locationRequest, callback, null);
    }

    public void pararLecturaPermanente() {
        if (callback != null) {
            fusedLocationClient.removeLocationUpdates(callback);
        }
    }

    public void actualizarMapa() {
        MapaActual mapaActual = new MapaActual();
        mMapaActual.setValue(mapaActual);
    }

    public class MapaActual implements OnMapReadyCallback {
        @Override
        public void onMapReady(GoogleMap googleMap) {
            mapa = googleMap;
            mapa.addMarker(new MarkerOptions().position(new LatLng(-33.264915009265884, -66.3107858010564)).title("Farmacia Franzzi"));
            mapa.addMarker(new MarkerOptions().position(new LatLng(-33.28498320540254, -66.31021894316767)).title("Farmacia Catamarca"));
            mapa.addMarker(new MarkerOptions().position(new LatLng(-33.28213861681986, -66.31519744482183)).title("Farmacia Muller"));
            mapa.addMarker(new MarkerOptions().position(new LatLng(-33.26585621977654, -66.3063831544183)).title("Farmacia Del Pilar"));
            mapa.addMarker(new MarkerOptions().position(new LatLng(-33.27191955702518, -66.30615279887765)).title("Farmacia Los Cerros"));
            mapa.addMarker(new MarkerOptions().position(new LatLng(-33.27397461203196, -66.30478724100566)).title("Farmacia MacroFarma"));
            actualizarMiMarca();

            if (!isCamaraMovida) {
                moverCamara();
                isCamaraMovida = true;
            }
        }

        private void actualizarMiMarca() {
            if (miMarca != null) {
                miMarca.remove();
            }

            miMarca = mapa.addMarker(new MarkerOptions()
                    .position(miUbicacion)
                    .title("Yo"));
        }

        private void moverCamara() {
            CameraPosition campos = new CameraPosition.Builder()
                    .target(miUbicacion)
                    .zoom(17)
                    .bearing(0)
                    .tilt(0)
                    .build();
            CameraUpdate update = CameraUpdateFactory.newCameraPosition(campos);
            mapa.animateCamera(update);
        }
    }

    public void obtenerTipoDeMapa(String tipoDeMapa) {
        // Verifica si mapa es null
        if (mapa == null) {
            return;
        }

        // Establece el tipo de mapa según la preferencia
        switch (tipoDeMapa) {
            case "terrain":
                mapa.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                break;
            case "hybrid":
                mapa.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                break;
            case "satellite":
                mapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                break;
            default:
                mapa.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                break;
        }
    }

}
