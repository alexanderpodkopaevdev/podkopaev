package com.alexanderpodkopaev.developerslifepost.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.alexanderpodkopaev.developerslifepost.R
import com.alexanderpodkopaev.developerslifepost.data.PostRepository
import com.alexanderpodkopaev.developerslifepost.data.PostRepositoryImpl
import com.alexanderpodkopaev.developerslifepost.data.RetrofitModule
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job


class PostsFragment : Fragment() {

    private lateinit var ivPostImage: ImageView
    private lateinit var ibBack: ImageButton
    private lateinit var ibNext: ImageButton
    private lateinit var tvDesc: TextView

    private lateinit var postRepository: PostRepository


    private var coroutineScope: CoroutineScope = CoroutineScope(Job() + Dispatchers.IO)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_posts, container, false)
        initView(view)
        postRepository = PostRepositoryImpl(RetrofitModule.devLifePostApi)
        val viewModel = ViewModelProvider(
            this,
            PostsViewModelFactory(postRepository)
        ).get(PostsViewModel::class.java)
        viewModel.currentPost.observe(viewLifecycleOwner) {
            Glide.with(requireContext())
                .load(it.gifURL)
                .transform(
                    RoundedCorners(
                        requireContext().resources.getDimension(
                            R.dimen.small
                        ).toInt()
                    )
                ).placeholder(R.drawable.progress_animation)
                .into(ivPostImage)
            tvDesc.text = it.description
        }
        viewModel.currentPostPosition.observe(viewLifecycleOwner) { currentPosition ->
            ibBack.isEnabled = currentPosition != 0
        }
        viewModel.isError.observe(viewLifecycleOwner) { isError ->
            if (isError) {
                ivPostImage.setImageResource(R.drawable.ic_launcher_foreground)
                tvDesc.text = getString(R.string.error_text)
            }
        }
        ibNext.setOnClickListener {
            viewModel.getNextPost()
        }
        ibBack.setOnClickListener {
            viewModel.getPrevPost()
        }

        return view
    }

    private fun initView(view: View) {
        ivPostImage = view.findViewById(R.id.ivPostImage)
        ibBack = view.findViewById(R.id.ibBack)
        ibNext = view.findViewById(R.id.ibNext)
        tvDesc = view.findViewById(R.id.tvDesc)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            PostsFragment()
    }
}