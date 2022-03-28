package com.example.mytranslator

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