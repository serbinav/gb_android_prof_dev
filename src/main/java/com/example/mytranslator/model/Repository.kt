package com.example.mytranslator.model

interface Repository<T> {

    fun getData(word: String): T

}