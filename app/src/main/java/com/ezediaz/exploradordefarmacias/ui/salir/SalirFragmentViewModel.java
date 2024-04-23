package com.ezediaz.exploradordefarmacias.ui.salir;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SalirFragmentViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public SalirFragmentViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}