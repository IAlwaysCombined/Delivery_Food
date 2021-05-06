package com.example.delivery_food.ui.fragment.screens.user

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.delivery_food.R
import com.example.delivery_food.databinding.FragmentCreateOrdersRestaurantBinding
import com.example.delivery_food.utilites.*


class CreateOrdersRestaurantFragment : Fragment(R.layout.fragment_create_orders_restaurant) {

   private lateinit var binding: FragmentCreateOrdersRestaurantBinding

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      binding = FragmentCreateOrdersRestaurantBinding.bind(view)
   }

   override fun onStart() {
      super.onStart()
      binding.btnCreateOrder.setOnClickListener { createOrder() }
      binding.orderRestaurantBtnBack.setOnClickListener { back() }
   }

   private fun back() {
      replaceFragment(AccountFragment())
   }

   //Create restaurant order
   private fun createOrder() {
      val dateMap = mutableMapOf<String, Any>()
      dateMap[CHILD_NAME] = binding.textInputEdtChangeName.text.toString()
      dateMap[CHILD_PHONE] = USER.phone
      dateMap[CHILD_ID] = UID
      REF_DATABASE_ROOT.child(NODE_ORDERS_CREATE_RESTAURANT).child(UID).updateChildren(dateMap)
         .addOnCompleteListener {task ->
            if(task.isSuccessful){
               showToast(getString(R.string.order_successfully_sent))
            } else showToast(getString(R.string.something_went_wrong_toast))
         }
   }
}