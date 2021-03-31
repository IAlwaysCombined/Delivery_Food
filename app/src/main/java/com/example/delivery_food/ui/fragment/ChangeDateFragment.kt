package com.example.delivery_food.ui.fragment

import androidx.fragment.app.Fragment
import com.example.delivery_food.MainActivity
import com.example.delivery_food.R
import com.example.delivery_food.utilites.*
import kotlinx.android.synthetic.main.fragment_chande_date.*
import kotlinx.android.synthetic.main.fragment_registration.*

class ChangeDateFragment : Fragment(R.layout.fragment_chande_date) {

    override fun onStart() {
        super.onStart()
        initFields()
        btn_back.setOnClickListener { back() }
        btn_save_changes.setOnClickListener { saveChangesDateUser() }
    }

    //Save Changes
    private fun saveChangesDateUser() {
        val uid = AUTH.currentUser?.uid.toString()
        val dateMap = mutableMapOf<String, Any>()
        dateMap[CHILD_NAME] = text_input_edt_change_name.text.toString()
        dateMap[CHILD_PHONE] = text_input_change_number_phone.text.toString()
        REF_DATABASE_ROOT.child(NODE_USERS).child(uid).updateChildren(dateMap)
            .addOnCompleteListener {task ->
                if(task.isSuccessful){
                    replaceFragment(AccountFragment())
                    showToast(getString(R.string.date_update))
                } else showToast(getString(R.string.something_went_wrong))
            }
    }

    //Back fragment account
    private fun back() {
        replaceFragment(AccountFragment())
    }

    //Initial Fields
    private fun initFields() {
        text_input_edt_change_name.setText(USER.name)
        text_input_change_number_phone.setText(USER.phone)
    }
}