package com.example.delivery_food.ui.fragment.screens.auth

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.fragment.app.Fragment
import com.example.delivery_food.MainActivity
import com.example.delivery_food.R
import com.example.delivery_food.databinding.FragmentRegistrationBinding
import com.example.delivery_food.utilites.*


class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    private lateinit var binding: FragmentRegistrationBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegistrationBinding.bind(view)
    }

    override fun onStart() {
        super.onStart()
        binding.btnRegistration.setOnClickListener { signUp() }
        initFirebase()
        binding.registrationBtnBack.setOnClickListener { backAuthFragment() }
    }

    //Replace fragment
    private fun backAuthFragment() {
        replaceFragmentAuth(AuthFragment())
    }

    //SignUp account
    private fun signUp() {
        when {
            TextUtils.isEmpty(binding.textInputEdtEmailRegistration.text.toString()) -> {
                showToast(getString(R.string.fill_email_toast))
                return
            }
            TextUtils.isEmpty(binding.textInputEdtPasswordRegistration.text.toString()) -> {
                showToast(getString(R.string.fill_password_toast))
                return
            }
            TextUtils.isEmpty(binding.textInputEdtNameRegistration.text.toString()) -> {
                showToast(getString(R.string.fill_name_toast))
                return
            }
            TextUtils.isEmpty(binding.textInputEdtPhoneNumberRegistration.text.toString()) -> {
                showToast(getString(R.string.fill_phone_number_toast))
                return
            }
            else -> AUTH.createUserWithEmailAndPassword(
                binding.textInputEdtEmailRegistration.text.toString(),
                binding.textInputEdtPasswordRegistration.text.toString()
            )
                .addOnCompleteListener {
                    val uid = AUTH.currentUser?.uid.toString()
                    val dateMap = mutableMapOf<String, Any>()
                    dateMap[CHILD_NAME] = binding.textInputEdtNameRegistration.text.toString()
                    dateMap[CHILD_PHONE] = binding.textInputEdtPhoneNumberRegistration.text.toString()
                    dateMap[CHILD_ROLE] = USER_ROLE
                    REF_DATABASE_ROOT.child(NODE_USERS).child(uid).updateChildren(dateMap)
                        .addOnCompleteListener {task ->
                            if(task.isSuccessful){
                                replaceActivity(MainActivity())
                            } else showToast(getString(R.string.something_went_wrong_toast))
                        }
                }
        }
    }
}