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

class BottomSheet : BottomSheetDialogFragment(R.layout.language_bottom_sheet) {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.language_bottom_sheet, container, false)

        val rus = view.findViewById<LinearLayout>(R.id.rus)
        val uzb = view.findViewById<LinearLayout>(R.id.uzb)
        val eng = view.findViewById<LinearLayout>(R.id.eng)
        val rusT = view.findViewById<ImageView>(R.id.rusTick)
        val engT = view.findViewById<ImageView>(R.id.engTick)
        val uzbT = view.findViewById<ImageView>(R.id.uzbTick)

        rus.setOnClickListener {
            rusT.visibility = View.VISIBLE
            engT.visibility = View.INVISIBLE
            uzbT.visibility = View.INVISIBLE
        }

        uzb.setOnClickListener {
            uzbT.visibility = View.VISIBLE
            engT.visibility = View.INVISIBLE
            rusT.visibility = View.INVISIBLE
        }
        eng.setOnClickListener {
            engT.visibility = View.VISIBLE
            rusT.visibility = View.INVISIBLE
            uzbT.visibility = View.INVISIBLE
        }

        return view

    }
}
