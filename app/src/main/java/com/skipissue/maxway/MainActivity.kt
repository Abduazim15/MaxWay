package com.skipissue.maxway

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.navigation.NavigationBarView
import com.skipissue.maxway.data.settings.Settings
import com.skipissue.maxway.databinding.ActivityMainBinding
import com.skipissue.maxway.presentation.fragments.BasketFragment
import com.skipissue.maxway.presentation.fragments.ConfirmFragment
import com.skipissue.maxway.presentation.fragments.MainFragment
import com.skipissue.maxway.presentation.fragments.OrdersFragment
import com.skipissue.maxway.presentation.fragments.PhoneFragment
import com.skipissue.maxway.presentation.fragments.ProfileFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by viewBinding()

    @Inject
    lateinit var settings: Settings
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        setContentView(R.layout.activity_main)
        settings.shipperId = "d0d0c7c9-e047-4ad8-9674-a94a27e3da73"
        binding.bottom.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.main -> {
                    supportFragmentManager.beginTransaction().setReorderingAllowed(true)
                        .replace(R.id.container, MainFragment()).commit()
                }

                R.id.basket -> {
                    if (settings.phoneNumber.isNullOrEmpty()) {
                        supportFragmentManager.beginTransaction().setReorderingAllowed(true).addToBackStack("MainFragment")
                            .replace(R.id.container, ConfirmFragment()).commit()
                    } else
                        supportFragmentManager.beginTransaction().setReorderingAllowed(true)
                            .replace(R.id.container, BasketFragment()).commit()
                }

                R.id.my_orders -> {
                    if (settings.phoneNumber.isNullOrEmpty()) {
                        supportFragmentManager.beginTransaction().setReorderingAllowed(true).addToBackStack("MainFragment")
                            .replace(R.id.container, PhoneFragment()).commit()
                    } else
                        supportFragmentManager.beginTransaction().setReorderingAllowed(true)
                            .replace(R.id.container, OrdersFragment()).commit()
                }

                R.id.profile -> {
                    supportFragmentManager.beginTransaction().setReorderingAllowed(true)
                        .replace(R.id.container, ProfileFragment()).commit()
                }
            }
            return@setOnItemSelectedListener true
        }
    }

    fun hideOrShow(hide: Boolean) {
        binding.bottom.visibility = if (hide) View.GONE else View.VISIBLE
    }
}