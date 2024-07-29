package jp.speakbuddy.edisonandroidexercise.data

import jp.speakbuddy.edisonandroidexercise.data.model.FactResponse
import jp.speakbuddy.edisonandroidexercise.network.FactService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CatFactRepository(private val api: FactService) {
    fun getCatFact(): Flow<FactResponse> = flow {
            emit(api.getFact())
    }
}