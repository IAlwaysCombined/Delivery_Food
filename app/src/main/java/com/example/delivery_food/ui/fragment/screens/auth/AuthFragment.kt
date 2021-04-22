package com.example.delivery_food.ui.fragment.screens.auth

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.fragment.app.Fragment
import com.example.delivery_food.MainActivity
import com.example.delivery_food.R
import com.example.delivery_food.databinding.FragmentAuthBinding
import com.example.delivery_food.utilites.AUTH
import com.example.delivery_food.utilites.replaceActivity
import com.example.delivery_food.utilites.replaceFragmentAuth
import com.example.delivery_food.utilites.showToast


class AuthFragment : Fragment(R.layout.fragment_auth) {

    private lateinit var binding: FragmentAuthBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAuthBinding.bind(view)
    }

    override fun onStart() {
        super.onStart()
        binding.authAccountTextView.setOnClickListener { replaceRegistrationFragment() }
        binding.authRestorePasswordTextView.setOnClickListener { restorePass() }
        binding.btnAuth.setOnClickListener { signIn() }
    }

    //Replace Fragment
    private fun replaceRegistrationFragment() {
        replaceFragmentAuth(RegistrationFragment())
    }

    //Restore password
    private fun restorePass() {
        if (TextUtils.isEmpty(binding.textInputEdtEmail.text)) {
            showToast(getString(R.string.fill_email_toast))
            return
        }
        val emailAddress = binding.textInputEdtEmail.text.toString()
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
        if (TextUtils.isEmpty(binding.textInputEdtEmail.text.toString())) {
            showToast(getString(R.string.fill_email_toast))
            return
        } else if (TextUtils.isEmpty(binding.textInputEdtPassword.text.toString())) {
            showToast(getString(R.string.fill_password_toast))
            return
        }
        AUTH.signInWithEmailAndPassword(
            binding.textInputEdtEmail.text.toString(),
            binding.textInputEdtPassword.text.toString()
        )
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