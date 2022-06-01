package com.sinngjpeg.challengegithub.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupActionBarWithNavController
import com.sinngjpeg.challengegithub.R
import com.sinngjpeg.challengegithub.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        Thread.sleep(1000L)
        setTheme(R.style.Theme_ChallengeGithub)
        setContentView(binding.root)
        navController = Navigation.findNavController(this, R.id.fragment_container_view)
        setupActionBarWithNavController(navController!!)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}