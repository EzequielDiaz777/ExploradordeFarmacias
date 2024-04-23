package com.ezediaz.exploradordefarmacias.ui.configuracion;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ConfiguracionFragmentViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ConfiguracionFragmentViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public String mapChecked(){

        return "";
    }
}