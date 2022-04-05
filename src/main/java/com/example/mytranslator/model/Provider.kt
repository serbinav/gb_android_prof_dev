package com.example.mytranslator.model

interface Provider<T> {

    fun getData(word: String): T

}