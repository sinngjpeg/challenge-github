package com.sinngjpeg.challengegithub.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sinngjpeg.challengegithub.R
import com.sinngjpeg.challengegithub.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        Thread.sleep(1000L)
        setTheme(R.style.Theme_ChallengeGithub)
        setContentView(binding.root)
    }
}
