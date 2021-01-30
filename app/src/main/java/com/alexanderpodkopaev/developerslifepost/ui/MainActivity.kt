package com.alexanderpodkopaev.developerslifepost.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alexanderpodkopaev.developerslifepost.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.frame, PostsFragment.newInstance())
                .commit()
        }

    }
}