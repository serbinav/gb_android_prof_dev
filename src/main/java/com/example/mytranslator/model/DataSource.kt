package com.example.mytranslator.model

interface DataSource<T> {

    fun getData(word: String): T

}