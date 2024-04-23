package com.ezediaz.exploradordefarmacias.ui.gallery;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GalleryFragmentViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public GalleryFragmentViewModel() {
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