package com.alexanderpodkopaev.developerslifepost.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexanderpodkopaev.developerslifepost.data.PostModel
import com.alexanderpodkopaev.developerslifepost.data.PostRepository
import kotlinx.coroutines.launch

class PostsViewModel(private val postRepository: PostRepository) : ViewModel() {

    private val _isError = MutableLiveData(false)
    val isError: LiveData<Boolean> = _isError

    private var _currentPostPosition = MutableLiveData<Int>(0)
    val currentPostPosition: LiveData<Int> = _currentPostPosition

    private val postList = mutableListOf<PostModel>()

    private val _currentPost = MutableLiveData<PostModel>().apply { getCurrentPost() }
    val currentPost: LiveData<PostModel> = _currentPost

    private fun getCurrentPost() {
        _isError.value = false
        val nextPost = postList.getOrNull(_currentPostPosition.value!!)
        if (nextPost == null) {
            loadPost()
        } else {
            _currentPost.value = nextPost
        }
    }

    private fun loadPost() {
        viewModelScope.launch {
            try {
                _isError.value = false
                val newPost = postRepository.getPost()
                postList.add(newPost)
                _currentPost.value = newPost
            } catch (ex: Exception) {
                _isError.value = true
            }
        }
    }

    fun getNextPost() {
        var position = _currentPostPosition.value!!
        _currentPostPosition.value = ++position
        getCurrentPost()
    }

    fun getPrevPost() {
        var position = _currentPostPosition.value!!
        _currentPostPosition.value = --position
        getCurrentPost()

    }


}