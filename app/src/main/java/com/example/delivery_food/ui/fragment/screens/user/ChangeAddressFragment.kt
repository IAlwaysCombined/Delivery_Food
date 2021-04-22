package com.example.delivery_food.ui.fragment.screens.user

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.fragment.app.Fragment
import com.example.delivery_food.R
import com.example.delivery_food.databinding.FragmentChangeAddressBinding
import com.example.delivery_food.utilites.*

class ChangeAddressFragment : Fragment(R.layout.fragment_change_address) {

    private lateinit var binding: FragmentChangeAddressBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentChangeAddressBinding.bind(view)
    }

    override fun onStart() {
        super.onStart()
        initFields()
        binding.addressBtnBack.setOnClickListener { backFragmentAccount() }
        binding.btnAddNewAddress.setOnClickListener { saveChangesAddress() }
    }

    //Save changes address
    private fun saveChangesAddress() {
        if (TextUtils.isEmpty(binding.textInputEdtChangeNewHouse.text.toString())) {
            showToast(getString(R.string.fil_house_toast))
            return
        }
        if (TextUtils.isEmpty(binding.textInputEdtChangeNewEntry.text.toString())) {
            showToast(getString(R.string.fill_entry_toast))
            return
        }
        if (TextUtils.isEmpty(binding.textInputEdtChangeNewFloor.text.toString())) {
            showToast(getString(R.string.fill_floor_toast))
            return
        }
        if (TextUtils.isEmpty(binding.textInputEdtChangeNewFlat.text.toString())) {
            showToast(getString(R.string.fill_flat_toast))
            return
        } else {
            val dateMap = mutableMapOf<String, Any>()
            dateMap[CHILD_HOUSE] = binding.textInputEdtChangeNewHouse.text.toString()
            dateMap[CHILD_ENTRY] = binding.textInputEdtChangeNewEntry.text.toString()
            dateMap[CHILD_FLOOR] = binding.textInputEdtChangeNewFloor.text.toString()
            dateMap[CHILD_FLAT] = binding.textInputEdtChangeNewFlat.text.toString()
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
        binding.textInputEdtChangeNewHouse.setText(ADDRESS.house)
        binding.textInputEdtChangeNewEntry.setText(ADDRESS.entry)
        binding.textInputEdtChangeNewFloor.setText(ADDRESS.floor)
        binding.textInputEdtChangeNewFlat.setText(ADDRESS.flat)
    }
}