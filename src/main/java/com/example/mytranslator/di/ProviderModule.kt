package com.example.mytranslator.di

import com.example.mytranslator.Constants
import com.example.mytranslator.model.ModelProvider
import com.example.mytranslator.model.Repository
import com.example.mytranslator.retrofit.ApiData
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class ProviderModule {

    @Provides
    internal fun provideInteractor(
        @Named(Constants.NAME_LOCAL) repositoryLocal: Repository<List<ApiData>>
    ) = ModelProvider(repositoryLocal)
}