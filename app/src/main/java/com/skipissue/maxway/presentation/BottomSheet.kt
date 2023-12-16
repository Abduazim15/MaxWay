package com.skipissue.maxway.presentation

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.skipissue.maxway.R
import com.skipissue.maxway.data.constants.Languages
import com.skipissue.maxway.data.settings.Settings
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BottomSheet : BottomSheetDialogFragment(R.layout.language_bottom_sheet) {
    @Inject
    lateinit var settings: Settings
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.language_bottom_sheet, container, false)

        val rus = view.findViewById<LinearLayout>(R.id.rus)
        val uzb = view.findViewById<LinearLayout>(R.id.uzb)
        val eng = view.findViewById<LinearLayout>(R.id.eng)
        val rusT = view.findViewById<ImageView>(R.id.rusTick)
        val engT = view.findViewById<ImageView>(R.id.engTick)
        val uzbT = view.findViewById<ImageView>(R.id.uzbTick)
        when (settings.language){
            Languages.UZB -> uzbT.visibility = View.VISIBLE
            Languages.ENG -> engT.visibility = View.VISIBLE
            Languages.RUS -> rusT.visibility = View.VISIBLE
        }

        rus.setOnClickListener {
            rusT.visibility = View.VISIBLE
            engT.visibility = View.INVISIBLE
            uzbT.visibility = View.INVISIBLE
            settings.language = Languages.RUS
        }

        uzb.setOnClickListener {
            uzbT.visibility = View.VISIBLE
            engT.visibility = View.INVISIBLE
            rusT.visibility = View.INVISIBLE
            settings.language = Languages.UZB
        }
        eng.setOnClickListener {
            engT.visibility = View.VISIBLE
            rusT.visibility = View.INVISIBLE
            uzbT.visibility = View.INVISIBLE
            settings.language = Languages.ENG
        }

        return view

    }
}
