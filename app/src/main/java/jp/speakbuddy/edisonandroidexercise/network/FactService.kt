package jp.speakbuddy.edisonandroidexercise.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import jp.speakbuddy.edisonandroidexercise.data.model.FactResponse
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

interface FactService {
    @GET("fact")
    suspend fun getFact(): FactResponse
}

object FactServiceProvider {
    fun provide(): FactService =
        Retrofit.Builder()
            .baseUrl("https://catfact.ninja/")
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(FactService::class.java)
}
