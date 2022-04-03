package com.example.mytranslator.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mytranslator.model.ModelProvider
import com.example.mytranslator.parseSearchResults
import kotlinx.coroutines.*

class MainViewModel(
    private val provider: ModelProvider
) : ViewModel() {

    private val liveDataForViewToObserve: MutableLiveData<AppState> = MutableLiveData()

    protected val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        })

    fun handleError(error: Throwable) {
        liveDataForViewToObserve.postValue(AppState.Error(error))
    }

    fun getData(word: String) {
        liveDataForViewToObserve.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch {
            startProvider(word)
        }
    }

    private suspend fun startProvider(word: String) = withContext(Dispatchers.IO) {
        liveDataForViewToObserve.postValue(parseSearchResults(provider.getData(word)))
    }

    protected fun cancelJob() {
        viewModelCoroutineScope.coroutineContext.cancelChildren()
    }

    override fun onCleared() {
        liveDataForViewToObserve.value = AppState.Success(null)
        super.onCleared()
        cancelJob()
    }

    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }
}