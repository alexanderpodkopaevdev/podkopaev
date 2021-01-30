package com.alexanderpodkopaev.developerslifepost.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.alexanderpodkopaev.developerslifepost.R
import com.alexanderpodkopaev.developerslifepost.data.PostModel
import com.alexanderpodkopaev.developerslifepost.data.RetrofitModule
import com.bumptech.glide.Glide
import kotlinx.coroutines.*


class PostsFragment : Fragment() {

    private lateinit var ivPostImage: ImageView
    private lateinit var ibBack: ImageButton
    private lateinit var ibNext: ImageButton
    private var post: PostModel? = null

    private var coroutineScope: CoroutineScope = CoroutineScope(Job() + Dispatchers.IO)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_posts, container, false)
        initView(view)
        getImage()
        ibNext.setOnClickListener {
            getImage()
        }

        return view
    }

    private fun getImage() {
        coroutineScope.launch {
            post = RetrofitModule.devLifePostApi.getRandomPost()
            withContext(Dispatchers.Main) {
                Glide.with(requireContext()).load(post?.gifURL).into(ivPostImage)
            }
        }
    }

    private fun initView(view: View) {
        ivPostImage = view.findViewById(R.id.ivPostImage)
        ibBack = view.findViewById(R.id.ibBack)
        ibNext = view.findViewById(R.id.ibNext)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            PostsFragment()
    }
}