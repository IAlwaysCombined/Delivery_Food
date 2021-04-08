package com.example.delivery_food.ui.fragment.screens.auth

import android.text.TextUtils
import androidx.fragment.app.Fragment
import com.example.delivery_food.MainActivity
import com.example.delivery_food.R
import com.example.delivery_food.utilites.*
import kotlinx.android.synthetic.main.fragment_registration.*


class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    override fun onStart() {
        super.onStart()
        btn_registration.setOnClickListener { signUp() }
        registration_account_text_view.setOnClickListener { backAuthFragment() }
        initFirebase()
    }

    //Replace fragment
    private fun backAuthFragment() {
        replaceFragmentAuth(AuthFragment())
    }

    //SignUp account
    private fun signUp() {
        when {
            TextUtils.isEmpty(text_input_edt_email_registration.text.toString()) -> {
                showToast(getString(R.string.fill_email_toast))
                return
            }
            TextUtils.isEmpty(text_input_edt_password_registration.text.toString()) -> {
                showToast(getString(R.string.fill_password_toast))
                return
            }
            TextUtils.isEmpty(text_input_edt_name_registration.text.toString()) -> {
                showToast(getString(R.string.fill_name_toast))
                return
            }
            TextUtils.isEmpty(text_input_edt_phone_number_registration.text.toString()) -> {
                showToast(getString(R.string.fill_phone_number_toast))
                return
            }
            else -> AUTH.createUserWithEmailAndPassword(
                text_input_edt_email_registration.text.toString(),
                text_input_edt_password_registration.text.toString()
            )
                .addOnCompleteListener {
                    val uid = AUTH.currentUser?.uid.toString()
                    val dateMap = mutableMapOf<String, Any>()
                    dateMap[CHILD_NAME] = text_input_edt_name_registration.text.toString()
                    dateMap[CHILD_PHONE] = text_input_edt_phone_number_registration.text.toString()
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