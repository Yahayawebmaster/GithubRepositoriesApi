package com.example.githubrepoapi.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.githubrepoapi.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val fragment = ProjectListFragment()

            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragment).commit()
        }
    }
}