package com.example.mytranslator.di

import com.example.mytranslator.Constants
import com.example.mytranslator.model.*
import com.example.mytranslator.retrofit.ApiData
import com.example.mytranslator.view_model.MainViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val application = module {
    single<Repository<List<ApiData>>>(named(Constants.NAME_REMOTE)) { RepositoryImplementation(RemoteModel()) }
    single<Repository<List<ApiData>>>(named(Constants.NAME_LOCAL)) { RepositoryImplementation(LocalModel()) }
}

val mainScreen = module {
    factory { ModelProvider(get(named(Constants.NAME_LOCAL))) }
    factory { MainViewModel(get()) }
}