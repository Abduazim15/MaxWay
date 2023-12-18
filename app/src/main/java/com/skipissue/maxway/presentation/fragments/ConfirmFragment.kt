package com.skipissue.maxway.presentation.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skipissue.maxway.MainActivity
import com.skipissue.maxway.R
import com.skipissue.maxway.data.settings.Settings
import com.skipissue.maxway.databinding.ConfirmFragmentBinding
import com.skipissue.maxway.presentation.viewmodels.ConfirmViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ConfirmFragment : Fragment(R.layout.confirm_fragment) {
    private val binding: ConfirmFragmentBinding by viewBinding()
    private val viewModel: ConfirmViewModel by viewModels()

    @Inject
    lateinit var settings: Settings
    private var code = ""
    private var fromTo = 0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fromTo = arguments?.getInt("tag")!!
        val editTexts = arrayOf(
            binding.box1,
            binding.box2,
            binding.box3,
            binding.box4,
            binding.box5,
            binding.box6
        )
        for (i in 0 until editTexts.size) {
            val currentEditText = editTexts[i]

            currentEditText.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus && currentEditText.text.isEmpty()) {
                    val emptyEditText = editTexts.firstOrNull { it.text.isEmpty() }
                    emptyEditText?.requestFocus()
                }
            }

            currentEditText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    if (s?.isEmpty() == true && i != 0) {
                        currentEditText.clearFocus()
                        val previousEditText = editTexts[i - 1]
                        previousEditText.requestFocus()
                    } else {
                        val nextEditText = editTexts.getOrNull(i + 1)
                        nextEditText?.requestFocus()
                    }
                }
            })

            currentEditText.setOnClickListener {
                if (currentEditText.text.isEmpty()) {
                    val emptyEditText = editTexts.firstOrNull { it.text.isEmpty() }
                    emptyEditText?.requestFocus()
                }
            }
            currentEditText.setOnKeyListener { _, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN) {
                    if (currentEditText.text.isEmpty() && i != 0) {
                        currentEditText.clearFocus()
                        val previousEditText = editTexts[i - 1]
                        previousEditText.setText("")
                        previousEditText.requestFocus()
                    }
                } else {
                    if (currentEditText.text.isNotEmpty() && i != editTexts.size - 1)
                        editTexts[i + 1].requestFocus()
                }
                false
            }
        }
        binding.submit.setOnClickListener {
            for (i in editTexts) {
                if (i.text.isNotBlank())
                    code += i.text.toString()
            }
            lifecycleScope.launch {
                viewModel.login(settings.phoneNumber!!, code)
            }
        }
        lifecycleScope.launch {
            viewModel.stateSuccess.collect { data ->
                settings.phoneNumber = data.phone
                settings.id = data.id
                settings.name = data.name
                settings.accessToken = data.access_token
                if (fromTo == BasketFragment.TAG)
                    parentFragmentManager.beginTransaction().setReorderingAllowed(true)
                        .replace(R.id.container, BasketFragment()).commit()
                else if (fromTo == OrdersFragment.TAG)
                    parentFragmentManager.beginTransaction().setReorderingAllowed(true)
                        .replace(R.id.container, OrdersFragment()).commit()
                else
                    parentFragmentManager.beginTransaction().setReorderingAllowed(true)
                        .replace(R.id.container, ProfileFragment()).commit()
                parentFragmentManager.popBackStackImmediate(R.id.container, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            }
        }
        lifecycleScope.launch {
            viewModel.errorFlow.collect { code ->

            }
        }
        lifecycleScope.launch {
            viewModel.networkFlow.collect {

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
