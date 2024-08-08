package com.anew.retrofitdataretrievepractice

import android.app.Dialog
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

object Utils {

    fun showCustomDialogueBox(context: Context) {
        val dialog = Dialog(context)

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.custom_dialogue_box)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val tvmessage: TextView = dialog.findViewById(R.id.tv_notification)
        val btn: Button = dialog.findViewById(R.id.btn_ok)

        btn.setOnClickListener {
            dialog.dismiss()
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }

        dialog.show()
    }

    fun checkInternetConnectivity(context: Context) {
        val connectivity: ConnectivityManager? = context.getSystemService(Service.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val info: NetworkInfo? = connectivity?.activeNetworkInfo

        if (info == null || info.state != NetworkInfo.State.CONNECTED) {
            showCustomDialogueBox(context)
        }
    }
}
