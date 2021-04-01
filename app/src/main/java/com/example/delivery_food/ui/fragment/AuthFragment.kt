package com.example.delivery_food.ui.fragment

import android.text.TextUtils
import androidx.fragment.app.Fragment
import com.example.delivery_food.MainActivity
import com.example.delivery_food.R
import com.example.delivery_food.utilites.AUTH
import com.example.delivery_food.utilites.replaceActivity
import com.example.delivery_food.utilites.replaceFragmentAuth
import com.example.delivery_food.utilites.showToast
import kotlinx.android.synthetic.main.fragment_auth.*


class AuthFragment : Fragment(R.layout.fragment_auth) {


    override fun onStart() {
        super.onStart()
        auth_account_text_view.setOnClickListener { replaceRegistrationFragment() }
        auth_restore_password_text_view.setOnClickListener { restorePass() }
        btn_auth.setOnClickListener{signIn()}
    }

    //Replace Fragment
    private fun replaceRegistrationFragment() {
        replaceFragmentAuth(RegistrationFragment())
    }

    //Restore password
    private fun restorePass() {
        if (TextUtils.isEmpty(text_input_edt_email.text)){
            showToast(getString(R.string.fill_email_toast))
            return
        }
        val emailAddress = text_input_edt_email.text.toString()
        AUTH.sendPasswordResetEmail(emailAddress)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    showToast(getString(R.string.message_change_password_send_toast))
                }
                else{
                    showToast(getString(R.string.something_went_wrong_toast))
                }
            }
    }

    //Change email and pass user and replace Activity
    private fun signIn() {
        if (TextUtils.isEmpty(text_input_edt_email.text.toString())) {
            showToast(getString(R.string.fill_email_toast))
            return
        }
        else if(TextUtils.isEmpty(text_input_edt_password.text.toString())){
            showToast(getString(R.string.fill_password_toast))
            return
        }
        AUTH.signInWithEmailAndPassword(text_input_edt_email.text.toString(), text_input_edt_password.text.toString())
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    replaceActivity(MainActivity())
                }
                else{
                    showToast(getString(R.string.something_went_wrong_toast))
                }
            }
    }
}