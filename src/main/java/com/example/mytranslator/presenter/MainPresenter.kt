package com.example.mytranslator.presenter

import com.example.mytranslator.MainContract
import com.example.mytranslator.model.MainModel

class MainPresenter<V : MainContract.View> : MainContract.Presenter<V> {

    private var currentView: V? = null
    private val model = MainModel()

    override fun attachView(view: V) {
        if (view != currentView) {
            currentView = view
        }
    }

    override fun detachView(view: V) {
        if (view == currentView) {
            currentView = null
        }
    }

    override fun getData(word: String) {
        currentView?.showUsers(model.getData())
    }
}