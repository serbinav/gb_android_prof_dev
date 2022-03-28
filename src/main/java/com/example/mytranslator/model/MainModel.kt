package com.example.mytranslator.model

class MainModel {
    private var data: List<WordTranslate> = arrayListOf(
        WordTranslate("school", "школа"),
        WordTranslate("schooling", "образование"),
        WordTranslate("schoolhouse", "здание школы")
    )

    fun getData(): List<WordTranslate> {
        return data;
    }
}