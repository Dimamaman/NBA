package com.example.nba

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.example.ViewPagerAdapter
import com.example.nba.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setUpTabs()
    }

    private fun setUpTabs() {
        val adapter = ViewPagerAdapter(supportFragmentManager,lifecycle)
        binding.contentMain.viewpager.adapter = adapter

        TabLayoutMediator(binding.contentMain.tablayout,binding.contentMain.viewpager) {tab, position ->
            when(position) {
                0 -> {tab.text = "Player"}
                1 -> {tab.text = "Teams"}
                2 -> {tab.text = "Games"}
            }
        }.attach()
    }
}