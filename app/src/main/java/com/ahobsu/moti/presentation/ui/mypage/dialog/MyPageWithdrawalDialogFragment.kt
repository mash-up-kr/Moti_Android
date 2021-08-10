package com.ahobsu.moti.presentation.ui.mypage.dialog

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.ahobsu.moti.R
import com.ahobsu.moti.Unit
import com.ahobsu.moti.presentation.ui.login.LoginActivity


class MyPageWithdrawalDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage(R.string.dialog_withdrawal)
                .setPositiveButton(R.string.yes) { dialog, id ->
                    Unit.removeToken()
                    context?.startActivity(Intent(context, LoginActivity::class.java))
                    (context as Activity).finish()
                    dismiss()
                }
                .setNegativeButton(R.string.cancel) { dialog, id ->
                    dismiss()
                }
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}