package com.skipissue.maxway.presentation.fragments

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skipissue.maxway.MainActivity
import com.skipissue.maxway.R
import com.skipissue.maxway.data.constants.Errors
import com.skipissue.maxway.data.settings.Settings
import com.skipissue.maxway.databinding.PhoneFragmentBinding
import com.skipissue.maxway.presentation.viewmodels.PhoneViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class PhoneFragment : Fragment(R.layout.phone_fragment) {
    private val binding: PhoneFragmentBinding by viewBinding()
    private val viewModel: PhoneViewModel by viewModels()
    private var fromTo: Int = 0
    @Inject
    lateinit var settings: Settings
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fromTo = arguments?.getInt("tag")!!
        binding.submit.setOnClickListener {
            viewModel.check("+998${binding.phone.text.toString()}")
            settings.phoneNumber = "+998${binding.phone.text.toString()}"
        }
        lifecycleScope.launch {
            viewModel.stateSuccess.collect { data ->
                parentFragmentManager.beginTransaction().addToBackStack("PhoneFragment")
                    .replace(R.id.container, ConfirmFragment::class.java, bundleOf("tag" to fromTo)).commit()
            }
        }
        lifecycleScope.launch {
            viewModel.stateCheck.collect { data ->
                viewModel.login(data.phone)
            }
        }
        lifecycleScope.launch {
            viewModel.errorFlow.collect { code ->
                if (code == Errors.IS_REGISTERED)
                    viewModel.register(settings.phoneNumber!!)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        (requireActivity() as MainActivity).hideOrShow(true)
    }

    override fun onDestroy() {
        super.onDestroy()
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)
        (requireActivity() as MainActivity).hideOrShow(false)

    }

}