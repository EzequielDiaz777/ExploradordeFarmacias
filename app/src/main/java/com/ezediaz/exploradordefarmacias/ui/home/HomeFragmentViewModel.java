package com.ezediaz.exploradordefarmacias.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ezediaz.exploradordefarmacias.R;
import com.ezediaz.exploradordefarmacias.modelo.Farmacia;

import java.util.ArrayList;
import java.util.List;

public class HomeFragmentViewModel extends AndroidViewModel {
    private MutableLiveData<List<Farmacia>> mutableLiveDataFarmacias;
    public HomeFragmentViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<List<Farmacia>> getMutableLiveDataFarmacias(){
        if(mutableLiveDataFarmacias == null){
            mutableLiveDataFarmacias = new MutableLiveData<>();
        }
        return mutableLiveDataFarmacias;
    }

    public void cargarFarmacias(){
        List<Farmacia> lista = new ArrayList<>();
        lista.add(new Farmacia("Farmacia Catamarca", "Catamarca 1784", R.drawable.catamarca, "8 a 12 y 15 a 20"));
        lista.add(new Farmacia("Farmacia Del Pilar","Zoilo Concha 154", R.drawable.delpilar, "6 a 22"));
        lista.add(new Farmacia("Farmacia Franzzi","Villa Fatima 470", R.drawable.franzi, "9 a 13 y 16 a 21:30"));
        lista.add(new Farmacia("Farmacia Los Cerros","Los Puntanos 278", R.drawable.loscerros, "9 a 22"));
        lista.add(new Farmacia("Farmacia MacroFarma","Juan Pekol 400", R.drawable.macrofarma, "8 a 23"));
        lista.add(new Farmacia("Farmacia Muller", "Martin de Loyola Este 653", R.drawable.muller, "8:30 a 13 y 17 a 22"));
        mutableLiveDataFarmacias.setValue(lista);
    }
}
