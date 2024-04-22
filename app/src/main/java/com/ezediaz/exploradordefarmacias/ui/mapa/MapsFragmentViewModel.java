package com.ezediaz.exploradordefarmacias.ui.mapa;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.util.Log;

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
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class MapsFragmentViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Location> mLocation;
    private MutableLiveData<MapaActual> mMapaActual;
    private FusedLocationProviderClient fused;
    public LatLng miUbicacion;
    private static final LatLng FRANZZI = new LatLng(-33.264915009265884, -66.3107858010564);
    private static final LatLng DELPILAR = new LatLng(-33.26585621977654, -66.3063831544183);
    private static final LatLng LOSCERROS = new LatLng(-33.27191955702518, -66.30615279887765);
    private static final LatLng MACROFARMA = new LatLng(-33.27397461203196, -66.30478724100566);
    private static final LatLng MULLER = new LatLng(-33.28213861681986, -66.31519744482183);
    private static final LatLng CATAMARCA = new LatLng(-33.28498320540254, -66.31021894316767);

    public MapsFragmentViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        fused = LocationServices.getFusedLocationProviderClient(getApplication());
    }

    public LiveData<Location> getMLocation(){
        if(mLocation == null){
            mLocation = new MutableLiveData<>();
        }
        return mLocation;
    }

    public LiveData<MapaActual> getMMapaActual(){
        if(mMapaActual == null){
            mMapaActual = new MutableLiveData<>();
        }
        return mMapaActual;
    }

    public void obtenerUltimaUbicacion() {
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d("salida", "No tengo los permisos habilitados");
            return;
        }
        Task<Location> tarea = fused.getLastLocation();
        tarea.addOnSuccessListener(getApplication().getMainExecutor(), new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    Log.d("salida", "Estoy en onSuccess");
                    mLocation.postValue(location);
                }
            }
        });
    }

    public void lecturaPermanente() {
        LocationRequest request = LocationRequest.create();//tiene informacion de cada cuanto tiempo quiero que haga lecturas y prioridad.
        request.setInterval(5000);
        request.setFastestInterval(5000);
        request.setPriority(Priority.PRIORITY_HIGH_ACCURACY);

        LocationCallback callback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                if (locationResult != null) {
                    Location location = locationResult.getLastLocation();
                    mLocation.postValue(location);
                }
            }
        };
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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

    public void obtenerMapa(){
        MapaActual ma = new MapaActual();
        mMapaActual.setValue(ma);
    }

    public class MapaActual implements OnMapReadyCallback {

        @Override
        public void onMapReady(@NonNull GoogleMap googleMap) {
            googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            googleMap.addMarker(new MarkerOptions().position(miUbicacion).title("Yo"));
            googleMap.addMarker(new MarkerOptions().position(FRANZZI).title("Farmacia Franzzi"));
            googleMap.addMarker(new MarkerOptions().position(CATAMARCA).title("Farmacia Catamarca"));
            googleMap.addMarker(new MarkerOptions().position(MULLER).title("Farmacia Muller"));
            googleMap.addMarker(new MarkerOptions().position(DELPILAR).title("Farmacia Del Pilar"));
            googleMap.addMarker(new MarkerOptions().position(LOSCERROS).title("Farmacia Los Cerros"));
            googleMap.addMarker(new MarkerOptions().position(MACROFARMA).title("Farmacia MacroFarma"));
            CameraPosition campos = new CameraPosition.Builder()
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
}
