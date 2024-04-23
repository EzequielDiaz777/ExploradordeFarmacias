package com.ezediaz.exploradordefarmacias;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class MainActivityViewModel extends AndroidViewModel {
    // Propiedades observables para tipo de mapa y idioma
    private final MutableLiveData<String> mTipoDeMapa = new MutableLiveData<>("normal");
    private static final MutableLiveData<String> mIdioma = new MutableLiveData<>("es");

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
    }

    // Métodos para obtener y establecer el tipo de mapa
    public LiveData<String> getTipoDeMapa() {
        return mTipoDeMapa;
    }

    public void setTipoDeMapa(String tipoDeMapa) {
        mTipoDeMapa.setValue(tipoDeMapa);
    }

    // Métodos para obtener y establecer el idioma
    public LiveData<String> getMIdioma() {
        return mIdioma;
    }

    public void setMIdioma(String idioma) {
        mIdioma.setValue(idioma);
    }
}
