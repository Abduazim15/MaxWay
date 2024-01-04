package com.skipissue.maxway.presentation.fragments

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skipissue.maxway.MainActivity
import com.skipissue.maxway.R
import com.skipissue.maxway.data.settings.Settings
import com.skipissue.maxway.databinding.UpdateFragmentBinding
import com.skipissue.maxway.domain.entity.UpdateEntity
import com.skipissue.maxway.presentation.viewmodels.UpdateViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class UpdateFragment : Fragment(R.layout.update_fragment) {
    private val binding: UpdateFragmentBinding by viewBinding()
    private val viewModel : UpdateViewModel by viewModels()
    @Inject
    lateinit var settings: Settings
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            name.setText(settings.name)
            phone.setText(settings.phoneNumber)
            phone.isEnabled = false
            submit.setOnClickListener {
                val name = binding.name.text.toString()
                val date = binding.date.text
                viewModel.update(UpdateEntity(name, settings.phoneNumber!!))
            }
            back.setOnClickListener {
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }
        }
        lifecycleScope.launch {
            viewModel.stateSuccess.collect { mesage ->
                settings.name = binding.name.text.toString()
                requireActivity().onBackPressedDispatcher.onBackPressed()
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