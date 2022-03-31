package com.example.mytranslator.view_model

import androidx.lifecycle.LiveData
import com.example.mytranslator.model.RemoteModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel(
    private val repository: RemoteModel = RemoteModel()
) : BaseViewModel<AppState>() {

    private var appState: AppState? = null

    override fun getData(word: String): LiveData<AppState> {
        compositeDisposable.add(
            repository.getData(word)
                .map { AppState.Success(it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { liveDataForViewToObserve.value = AppState.Loading(null) }
                .subscribeWith(getObserver())
        )
        return super.getData(word)
    }

    private fun getObserver(): DisposableSingleObserver<AppState> {
        return object : DisposableSingleObserver<AppState>() {

            override fun onError(e: Throwable) {
                liveDataForViewToObserve.value = AppState.Error(e)
            }

            override fun onSuccess(state: AppState) {
                appState = state
                liveDataForViewToObserve.value = state
            }
        }
    }
}