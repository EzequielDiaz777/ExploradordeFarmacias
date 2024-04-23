package com.ezediaz.exploradordefarmacias.ui.mapa;

import android.Manifest;
import android.util.Log;
import android.widget.Toast;
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.HashMap;
import java.util.Map;

public class MapsFragmentViewModel extends AndroidViewModel {
    // Contexto y propiedades relacionadas con la ubicación y el mapa
    private final Context context;
    private MutableLiveData<Location> mLocation;
    public String mapType = "";
    private LocationCallback callback;
    private MutableLiveData<MapaActual> mMapaActual;
    private FusedLocationProviderClient fused;
    public LatLng miUbicacion;
    private static final LatLng FRANZZI = new LatLng(-33.264915009265884, -66.3107858010564);
    private static final LatLng DELPILAR = new LatLng(-33.26585621977654, -66.3063831544183);
    private static final LatLng LOSCERROS = new LatLng(-33.27191955702518, -66.30615279887765);
    private static final LatLng MACROFARMA = new LatLng(-33.27397461203196, -66.30478724100566);
    private static final LatLng MULLER = new LatLng(-33.28213861681986, -66.31519744482183);
    private static final LatLng CATAMARCA = new LatLng(-33.28498320540254, -66.31021894316767);

    // Constructor
    public MapsFragmentViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        fused = LocationServices.getFusedLocationProviderClient(getApplication());
    }

    // Métodos para obtener LiveData de la ubicación y del estado del mapa
    public LiveData<Location> getMLocation() {
        if (mLocation == null) {
            mLocation = new MutableLiveData<>();
        }
        return mLocation;
    }

    public LiveData<MapaActual> getMMapaActual() {
        if (mMapaActual == null) {
            mMapaActual = new MutableLiveData<>();
        }
        return mMapaActual;
    }

    // Método para obtener la última ubicación conocida
    /*public void obtenerUltimaUbicacion() {
        // Verifica los permisos antes de solicitar la ubicación
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d("salida", "No tengo los permisos habilitados");
            return;
        }

        // Tarea para obtener la última ubicación conocida
        Task<Location> tarea = fused.getLastLocation();
        tarea.addOnSuccessListener(getApplication().getMainExecutor(), new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    Log.d("salida", "Estoy en onSuccess");
                    mLocation.postValue(location); // Actualiza el LiveData de la ubicación
                }
            }
        });
    }*/

    // Método para iniciar la lectura continua de la ubicación
    public void lecturaPermanente() {
        LocationRequest request = new LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 5000).build();
        callback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                mLocation.postValue(locationResult.getLastLocation());
            }
        };
        // Inicia las actualizaciones de ubicación
        if (ActivityCompat.checkSelfPermission(getApplication(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplication(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fused.requestLocationUpdates(request, callback, null);
    }

    public void pararLecturaPermanente(){
        fused.removeLocationUpdates(callback);
    }

    // Método para crear el estado del mapa actual
    public void obtenerMapa() {
        MapaActual ma = new MapaActual();
        mMapaActual.setValue(ma); // Actualiza el LiveData del mapa actual
    }

    // Clase interna para manejar el mapa actual
    public class MapaActual implements OnMapReadyCallback {
        // Mapa de marcas y marcador de la posición actual del usuario
        private Map<String, Marker> markers = new HashMap<>();
        private Marker markerYo;

        @Override
        public void onMapReady(@NonNull GoogleMap googleMap) {
            // Configura el tipo de mapa según la preferencia
            switch (mapType) {
                case "MAP_TYPE_NONE":
                    googleMap.setMapType(0);
                    break;
                case "MAP_TYPE_NORMAL":
                    googleMap.setMapType(1);
                    break;
                case "MAP_TYPE_TERRAIN":
                    googleMap.setMapType(3);
                    break;
                case "MAP_TYPE_HYBRID":
                    googleMap.setMapType(4);
                    break;
                default:
                    googleMap.setMapType(2);
                    break;
            }

            googleMap.addMarker(new MarkerOptions().position(FRANZZI).title("Farmacia Franzzi"));
            googleMap.addMarker(new MarkerOptions().position(CATAMARCA).title("Farmacia Catamarca"));
            googleMap.addMarker(new MarkerOptions().position(MULLER).title("Farmacia Muller"));
            googleMap.addMarker(new MarkerOptions().position(DELPILAR).title("Farmacia Del Pilar"));
            googleMap.addMarker(new MarkerOptions().position(LOSCERROS).title("Farmacia Los Cerros"));
            googleMap.addMarker(new MarkerOptions().position(MACROFARMA).title("Farmacia MacroFarma"));
            googleMap.addMarker(new MarkerOptions().position(miUbicacion).title("Yo"));
            Toast.makeText(context, "Se creó el marcador de 'Yo'.", Toast.LENGTH_SHORT).show();

            // Configura la cámara para enfocar la posición actual del usuario
            CameraPosition campos =new CameraPosition.Builder()
                    .target(miUbicacion)
                    .zoom(14)
                    .bearing(45)
                    .tilt(70)
                    .build();
            CameraUpdate update = CameraUpdateFactory.newCameraPosition(campos);
            googleMap.animateCamera(update);
        }
    }
}
