package com.skipissue.maxway

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.navigation.NavigationBarView
import com.skipissue.maxway.databinding.ActivityMainBinding
import com.skipissue.maxway.presentation.fragments.MainFragment
import com.skipissue.maxway.presentation.fragments.ProfileFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding : ActivityMainBinding by viewBinding()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        setContentView(R.layout.activity_main)
        binding.bottom.setOnItemSelectedListener {
            when(it.itemId){
                R.id.main -> {supportFragmentManager.beginTransaction().setReorderingAllowed(true).replace(R.id.container, MainFragment()).commit()}
                R.id.basket -> {supportFragmentManager.beginTransaction().setReorderingAllowed(true).replace(R.id.container, MainFragment())}
                R.id.my_orders -> {supportFragmentManager.beginTransaction().setReorderingAllowed(true).replace(R.id.container, MainFragment())}
                R.id.profile -> {supportFragmentManager.beginTransaction().setReorderingAllowed(true).replace(R.id.container, ProfileFragment()).commit()}
            }
            return@setOnItemSelectedListener true
        }
    }
    fun hideOrShow(hide: Boolean){
        binding.bottom.visibility = if (hide) View.GONE else View.VISIBLE
    }
}