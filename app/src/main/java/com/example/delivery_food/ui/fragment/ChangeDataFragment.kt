package com.example.delivery_food.ui.fragment

import androidx.fragment.app.Fragment
import com.example.delivery_food.R
import com.example.delivery_food.utilites.*
import kotlinx.android.synthetic.main.fragment_change_data.*

class ChangeDataFragment : Fragment(R.layout.fragment_change_data) {

    override fun onStart() {
        super.onStart()
        initFields()
        data_btn_back.setOnClickListener { backFragmentAccount() }
        btn_save_changes.setOnClickListener { saveChangesDataUser() }
    }

    //Save changes data
    private fun saveChangesDataUser() {
        val dateMap = mutableMapOf<String, Any>()
        dateMap[CHILD_NAME] = text_input_edt_change_name.text.toString()
        dateMap[CHILD_PHONE] = text_input_change_number_phone.text.toString()
        REF_DATABASE_ROOT.child(NODE_USERS).child(UID).updateChildren(dateMap)
            .addOnCompleteListener {task ->
                if(task.isSuccessful){
                    replaceFragment(AccountFragment())
                    showToast(getString(R.string.date_update_toast))
                } else showToast(getString(R.string.something_went_wrong_toast))
            }
    }

    //Back fragment account
    private fun backFragmentAccount() {
        replaceFragment(AccountFragment())
    }

    //Initial fields
    private fun initFields() {
        text_input_edt_change_name.setText(USER.name)
        text_input_change_number_phone.setText(USER.phone)
    }
}