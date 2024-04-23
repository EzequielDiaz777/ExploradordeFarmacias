package com.ezediaz.exploradordefarmacias;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.ezediaz.exploradordefarmacias.modelo.Farmacia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {
    public String idioma ="";
    public String tipoMapa = "";
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
    }


}