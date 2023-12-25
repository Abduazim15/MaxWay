package com.skipissue.maxway.presentation.fragments

 import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.google.i18n.phonenumbers.Phonenumber
import com.skipissue.maxway.MainActivity
import com.skipissue.maxway.R
import com.skipissue.maxway.data.settings.Settings
import com.skipissue.maxway.databinding.ProfileFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.profile_fragment) {
    private val binding: ProfileFragmentBinding by viewBinding()
    @Inject
    lateinit var settings: Settings
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.settings.setOnClickListener {
            (requireActivity() as MainActivity).hideOrShow(true)
            requireParentFragment().parentFragmentManager.beginTransaction().setReorderingAllowed(true)
                .addToBackStack("ProfileFragment").replace(R.id.container, SettingsFragment())
                .commit()
        }
        binding.name.setText(settings.name)
        binding.number.setText(formatPhoneNumber(settings.phoneNumber!!))
    }
    fun formatPhoneNumber(phoneNumber: String): String {
        val phoneNumberUtil = PhoneNumberUtil.getInstance()
        val parsedNumber: Phonenumber.PhoneNumber = phoneNumberUtil.parse(phoneNumber, null)

        // Check if the phone number is valid
        if (phoneNumberUtil.isValidNumber(parsedNumber)) {
            // Format the valid phone number
            return phoneNumberUtil.format(parsedNumber, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL)
        }

        // Return the original phone number if it's not valid
        return phoneNumber
    }

}