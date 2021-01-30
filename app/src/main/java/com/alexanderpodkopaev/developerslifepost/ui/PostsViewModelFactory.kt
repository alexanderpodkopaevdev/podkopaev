package com.alexanderpodkopaev.developerslifepost.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alexanderpodkopaev.developerslifepost.data.PostRepository

class PostsViewModelFactory(private val postRepository: PostRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        PostsViewModel::class.java -> PostsViewModel(postRepository)
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}