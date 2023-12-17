package com.miu.foodiepal_culinarycompanion

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.miu.foodiepal_culinarycompanion.databinding.FragmentContactBinding


class ContactFragment : Fragment() {
    private lateinit var fragmentBinding:FragmentContactBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentBinding = FragmentContactBinding.inflate(inflater, container, false)

        val phoneBtn:TextView = fragmentBinding.cheafPhone
        val emailBtn:TextView = fragmentBinding.cheafEmail

        phoneBtn.setOnClickListener {
            val phoneNumber = fragmentBinding.cheafPhone.text.toString()
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:$phoneNumber")
            startActivity(dialIntent)
        }
        emailBtn.setOnClickListener {
            val emailAddress = fragmentBinding.cheafEmail.text.toString()
            val subject = "Thank you for your wanderful meal"
            val body = "Dear Cheaf, \n Thank you very much for your wonderful mead we had in your restaurant.\nRegards"

            val sendEmailInted = Intent(Intent.ACTION_SEND)
            sendEmailInted.type = "message/rfc822"
            //sendEmailInted.data = Uri.parse("mailto:$emailAddress")
            sendEmailInted.putExtra(Intent.EXTRA_EMAIL, arrayOf(emailAddress))
            sendEmailInted.putExtra(Intent.EXTRA_SUBJECT, subject)
            sendEmailInted.putExtra(Intent.EXTRA_TEXT, body)

            if(sendEmailInted.resolveActivity(requireContext().packageManager)!=null){
                startActivity(sendEmailInted)
            }else{
                Toast.makeText(context, "No email app installed", Toast.LENGTH_LONG).show()
            }
        }
        return fragmentBinding.root
    }
}