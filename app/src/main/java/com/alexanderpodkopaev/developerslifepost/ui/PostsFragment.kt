package com.alexanderpodkopaev.developerslifepost.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alexanderpodkopaev.developerslifepost.R


class PostsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_posts, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            PostsFragment()
    }
}