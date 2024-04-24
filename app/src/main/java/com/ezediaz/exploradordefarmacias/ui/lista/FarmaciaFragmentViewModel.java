package com.ezediaz.exploradordefarmacias.ui.lista;

import android.app.Application;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.ezediaz.exploradordefarmacias.modelo.Farmacia;

public class FarmaciaFragmentViewModel extends AndroidViewModel {
    private MutableLiveData<Farmacia> mFarmacia;
    public FarmaciaFragmentViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<Farmacia> getMFarmacia() {
        if(mFarmacia == null){
            mFarmacia = new MutableLiveData<>();
        }
        return mFarmacia;
    }
    public void cargarFarmacia(Bundle arguments) {
        if (arguments != null) {
            Farmacia farmacia = (Farmacia) arguments.getSerializable("farmacia");
            if (farmacia != null) {
                mFarmacia.setValue(farmacia);
            }
        }
    }
}
