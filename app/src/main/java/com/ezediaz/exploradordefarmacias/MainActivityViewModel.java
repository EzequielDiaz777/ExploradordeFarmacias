package com.ezediaz.exploradordefarmacias;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class MainActivityViewModel extends AndroidViewModel {
    // Propiedades observables para tipo de mapa y idioma
    private final MutableLiveData<String> mapType = new MutableLiveData<>();
    private final MutableLiveData<String> language = new MutableLiveData<>();

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
    }

    // Métodos para obtener y establecer el tipo de mapa
    public LiveData<String> getMapType() {
        return mapType;
    }

    public void setMapType(String type) {
        mapType.setValue(type);
    }

    // Métodos para obtener y establecer el idioma
    public LiveData<String> getLanguage() {
        return language;
    }

    public void setLanguage(String lang) {
        language.setValue(lang);
    }
}
