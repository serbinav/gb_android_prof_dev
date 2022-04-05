package com.example.mytranslator.model

import com.example.mytranslator.Constants
import com.example.mytranslator.retrofit.ApiData
import com.example.mytranslator.view_model.AppState
import javax.inject.Inject
import javax.inject.Named

class ModelProvider @Inject constructor(
    @Named(Constants.NAME_REMOTE)  val repositoryRemote: Repository<List<ApiData>>,
) : Provider<AppState> {

    override fun getData(word: String): AppState {
        return AppState.Success(repositoryRemote.getData(word))
    }
}