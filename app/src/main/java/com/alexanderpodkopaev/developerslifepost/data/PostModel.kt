package com.alexanderpodkopaev.developerslifepost.data

import kotlinx.serialization.Serializable

@Serializable
data class PostModel(
    val id: Int,
    val author: String,
    val date: String,
    val description: String,
    val gifURL: String,
    val height: String,
    val width: String,
    val previewURL: String,
    val type: String
)