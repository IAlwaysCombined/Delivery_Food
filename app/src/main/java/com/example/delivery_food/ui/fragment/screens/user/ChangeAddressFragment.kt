package com.example.delivery_food.ui.fragment.screens.user

import android.text.TextUtils
import androidx.fragment.app.Fragment
import com.example.delivery_food.R
import com.example.delivery_food.utilites.*
import kotlinx.android.synthetic.main.fragment_change_address.*

class ChangeAddressFragment : Fragment(R.layout.fragment_change_address) {

    override fun onStart() {
        super.onStart()
        initFields()
        address_btn_back.setOnClickListener { backFragmentAccount() }
        btn_add_new_address.setOnClickListener { saveChangesAddress() }
    }

    //Save changes address
    private fun saveChangesAddress() {
        if (TextUtils.isEmpty(text_input_edt_change_new_house.text.toString())) {
            showToast(getString(R.string.fil_house_toast))
            return
        }
        if (TextUtils.isEmpty(text_input_edt_change_new_entry.text.toString())) {
            showToast(getString(R.string.fill_entry_toast))
            return
        }
        if (TextUtils.isEmpty(text_input_edt_change_new_floor.text.toString())) {
            showToast(getString(R.string.fill_floor_toast))
            return
        }
        if (TextUtils.isEmpty(text_input_edt_change_new_flat.text.toString())) {
            showToast(getString(R.string.fill_flat_toast))
            return
        } else {
            val dateMap = mutableMapOf<String, Any>()
            dateMap[CHILD_HOUSE] = text_input_edt_change_new_house.text.toString()
            dateMap[CHILD_ENTRY] = text_input_edt_change_new_entry.text.toString()
            dateMap[CHILD_FLOOR] = text_input_edt_change_new_floor.text.toString()
            dateMap[CHILD_FLAT] = text_input_edt_change_new_flat.text.toString()
            REF_DATABASE_ROOT.child(NODE_ADDRESS).child(UID).updateChildren(dateMap)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        replaceFragment(AccountFragment())
                        showToast(getString(R.string.change_address_fragment_address_add_toast))
                    } else showToast(getString(R.string.something_went_wrong_toast))
                }
        }
    }

    //Back fragment account
    private fun backFragmentAccount() {
        replaceFragment(AccountFragment())
    }

    //Initial fields
    private fun initFields() {
        text_input_edt_change_new_house.setText(ADDRESS.house)
        text_input_edt_change_new_entry.setText(ADDRESS.entry)
        text_input_edt_change_new_floor.setText(ADDRESS.floor)
        text_input_edt_change_new_flat.setText(ADDRESS.flat)
    }
}