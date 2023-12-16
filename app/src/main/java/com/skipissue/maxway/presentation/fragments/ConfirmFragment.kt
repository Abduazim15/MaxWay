package com.skipissue.maxway.presentation.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skipissue.maxway.R
import com.skipissue.maxway.databinding.ConfirmFragmentBinding

class ConfirmFragment : Fragment(R.layout.confirm_fragment) {
    private val binding: ConfirmFragmentBinding by viewBinding()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
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
                }
                else {
                    if (currentEditText.text.isNotEmpty()&&i != editTexts.size-1)
                        editTexts[i+1].requestFocus()
                }
                false
            }
        }
        binding.submit.setOnClickListener {

        }
    }

}
