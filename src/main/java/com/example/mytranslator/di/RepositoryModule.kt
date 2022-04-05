package com.example.mytranslator.di

import com.example.mytranslator.Constants
import com.example.mytranslator.model.*
import com.example.mytranslator.retrofit.ApiData
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    @Named(Constants.NAME_REMOTE)
    internal fun provideRepositoryRemote(@Named(Constants.NAME_REMOTE) dataSourceRemote: DataSource<List<ApiData>>):
            Repository<List<ApiData>> =
        RepositoryImplementation(dataSourceRemote)

    @Provides
    @Singleton
    @Named(Constants.NAME_LOCAL)
    internal fun provideRepositoryLocal(@Named(Constants.NAME_LOCAL) dataSourceLocal: DataSource<List<ApiData>>):
            Repository<List<ApiData>> =
        RepositoryImplementation(dataSourceLocal)

    @Provides
    @Singleton
    @Named(Constants.NAME_REMOTE)
    internal fun provideDataSourceRemote(): DataSource<List<ApiData>> = RemoteModel()

    @Provides
    @Singleton
    @Named(Constants.NAME_LOCAL)
    internal fun provideDataSourceLocal(): DataSource<List<ApiData>> =
        LocalModel()
}