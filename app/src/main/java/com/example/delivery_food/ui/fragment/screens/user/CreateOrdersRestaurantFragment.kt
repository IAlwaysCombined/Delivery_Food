package com.example.delivery_food.ui.fragment.screens.user

import androidx.fragment.app.Fragment
import com.example.delivery_food.R
import com.example.delivery_food.utilites.*
import kotlinx.android.synthetic.main.fragment_create_orders_restaurant.*


class CreateOrdersRestaurantFragment : Fragment(R.layout.fragment_create_orders_restaurant) {

   override fun onStart() {
      super.onStart()
      btn_create_order.setOnClickListener { createOrder() }
   }

   //Create restaurant order
   private fun createOrder() {
      val dateMap = mutableMapOf<String, Any>()
      dateMap[CHILD_NAME] = text_input_edt_change_name.text.toString()
      dateMap[CHILD_PHONE] = USER.phone
      dateMap[CHILD_ID] = UID
      showToast(UID)
      REF_DATABASE_ROOT.child(NODE_ORDERS_CREATE_RESTAURANT).child(UID).updateChildren(dateMap)
         .addOnCompleteListener {task ->
            if(task.isSuccessful){
               showToast(getString(R.string.order_successfully_sent))
            } else showToast(getString(R.string.something_went_wrong_toast))
         }
   }
}