package com.example.delivery_food.ui.fragment.screens.user

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.delivery_food.R
import com.example.delivery_food.databinding.FragmentChangeDataBinding
import com.example.delivery_food.utilites.*

class ChangeDataFragment : Fragment(R.layout.fragment_change_data) {

    private lateinit var binding: FragmentChangeDataBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentChangeDataBinding.bind(view)
    }

    override fun onStart() {
        super.onStart()
        initFields()
        binding.orderRestaurantBtnBack.setOnClickListener { backFragmentAccount() }
        binding.btnSaveChanges.setOnClickListener { saveChangesDataUser() }
    }

    //Save changes data
    private fun saveChangesDataUser() {
        val dateMap = mutableMapOf<String, Any>()
        dateMap[CHILD_NAME] = binding.textInputEdtChangeName.text.toString()
        dateMap[CHILD_PHONE] = binding.textInputChangeNumberPhone.text.toString()
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
        binding.textInputEdtChangeName.setText(USER.name)
        binding.textInputChangeNumberPhone.setText(USER.phone)
    }
}