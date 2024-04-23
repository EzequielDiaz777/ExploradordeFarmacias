package com.ezediaz.exploradordefarmacias.ui.lista;

import android.app.Application;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.ezediaz.exploradordefarmacias.modelo.Farmacia;

public class FarmaciaActivityViewModel extends AndroidViewModel {
    private MutableLiveData<Farmacia> mutableFarmacia;
    private Farmacia farmacia;
    public FarmaciaActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<Farmacia> getMutableFarmacia(){
        if(mutableFarmacia == null){
            mutableFarmacia = new MutableLiveData<>();
        }
        return mutableFarmacia;
    }

    public void cargarFarmacia(Intent intent){
        farmacia = (Farmacia) intent.getSerializableExtra("farmacia");
        if(farmacia != null) {
            mutableFarmacia.setValue(farmacia);
        }
    }
}
