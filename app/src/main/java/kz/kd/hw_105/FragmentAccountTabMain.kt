package kz.kd.hw_105

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment

class FragmentAccountTabMain : Fragment(R.layout.vp_account_tab_main) {

    companion object {
        fun getFragment(): FragmentAccountTabMain {
            return FragmentAccountTabMain()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnShare: Button = view.findViewById(R.id.btn_share)
        btnShare.setOnClickListener {
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Here is my profile")
            sendIntent.type = "text/plain"
            startActivity(sendIntent)
        }

        val btnSend: Button = view.findViewById(R.id.btn_email)
        btnSend.setOnClickListener {
            val emailIntent = Intent()
            emailIntent.action = Intent.ACTION_SENDTO
            emailIntent.data = Uri.parse("mailto:")
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Here is my profile")
//            if (emailIntent.resolveActivity(packageManager) != null) {
//                startActivity(emailIntent)
//            }
        }
        val btnCall: Button = view.findViewById(R.id.btn_call)
        btnCall.setOnClickListener {
            val callIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "+7 777 777 77 77"))
            startActivity(callIntent)
        }
        val btnCamera: Button = view.findViewById(R.id.btn_camera)
        btnCamera.setOnClickListener {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivity(cameraIntent)
        }
    }
}