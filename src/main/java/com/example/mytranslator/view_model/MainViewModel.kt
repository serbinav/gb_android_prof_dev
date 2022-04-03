package com.example.mytranslator.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mytranslator.model.ModelProvider
import com.example.mytranslator.parseSearchResults

class MainViewModel(
    private val provider: ModelProvider
) : ViewModel() {

    private val liveDataForViewToObserve: MutableLiveData<AppState> = MutableLiveData()

    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    fun getData(word: String) {
        liveDataForViewToObserve.value = AppState.Loading(null)
        liveDataForViewToObserve.postValue(parseSearchResults(provider.getData(word)))
    }

    override fun onCleared() {
        liveDataForViewToObserve.value = AppState.Success(null)
    }
}