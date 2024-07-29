package jp.speakbuddy.edisonandroidexercise.data.model

import kotlinx.serialization.Serializable

@Serializable
data class FactResponse(
    val fact: String,
    val length: Int
)
