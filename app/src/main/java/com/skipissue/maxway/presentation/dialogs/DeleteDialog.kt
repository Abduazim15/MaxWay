package com.skipissue.maxway.presentation.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.skipissue.maxway.R

class DeleteDialog(context: Context, private val listener: OnSubmitClickListener) : Dialog(context) {

    init {
        setContentView(R.layout.close_dialog)

        val cancel: FrameLayout = findViewById(R.id.cancel)
        val submit: FrameLayout = findViewById(R.id.submit)

        cancel.setOnClickListener {
            listener.onAddClick(false)
            dismiss()
        }

        submit.setOnClickListener {
            listener.onAddClick(true)
            dismiss()
        }

        window?.apply {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setLayout(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )
        }
    }
}
fun interface OnSubmitClickListener {
    fun onAddClick(isOk: Boolean)
}