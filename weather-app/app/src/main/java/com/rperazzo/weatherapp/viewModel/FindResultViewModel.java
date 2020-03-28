package com.rperazzo.weatherapp.viewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.rperazzo.weatherapp.entities.FindResult;

public class FindResultViewModel extends ViewModel {

    private MutableLiveData<FindResult> result;

    public FindResultViewModel(){
        result = new MutableLiveData<>();
    }

    public MutableLiveData<FindResult> getFindResultLiveData() {
        return result;
    }

    public void carregarResultado(FindResult meuResult){
        this.result.setValue(meuResult);
    }

}
