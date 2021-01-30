package com.alexanderpodkopaev.developerslifepost.data

interface PostRepository {
    suspend fun getPost(): PostModel
}