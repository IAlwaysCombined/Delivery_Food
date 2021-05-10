package com.example.delivery_food.ui.fragment.screens.user

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.delivery_food.R
import com.example.delivery_food.databinding.FragmentCreateOrdersRestaurantBinding
import com.example.delivery_food.utilites.*
import com.theartofdev.edmodo.cropper.CropImage


class CreateOrdersRestaurantFragment : Fragment(R.layout.fragment_create_orders_restaurant) {

   private lateinit var binding: FragmentCreateOrdersRestaurantBinding

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      binding = FragmentCreateOrdersRestaurantBinding.bind(view)
   }

   override fun onStart() {
      super.onStart()
      binding.btnCreateOrder.setOnClickListener {  }
      binding.orderRestaurantBtnBack.setOnClickListener { back() }
      binding.imageRestaurant.setOnClickListener { setRestaurantPhoto() }
   }

   private fun back() {
      replaceFragment(AccountFragment())
   }

   private fun setRestaurantPhoto() {
      CropImage.activity()
         .setRequestedSize(1000,600)
         .start(APP_ACTIVITY, this)
   }

   //Get picture
   override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
      super.onActivityResult(requestCode, resultCode, data)
      if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE
         && resultCode == Activity.RESULT_OK && data != null
      ) {
         val uri = CropImage.getActivityResult(data).uri
         val path = REF_STORAGE_ROOT.child(FOLDER_PHOTO_RESTAURANT).child(UID)
         putFileToStorage(uri, path) {
            getUrlFromStorage(path) {
               putUrlToDatabase(it) {
                  binding.imageRestaurant.downloadAndSetImage(it)
                  showToast("Данные обновлены")
                  COMMON.image_restaurant = it
                  val dateMap = mutableMapOf<String, Any>()
                  dateMap[CHILD_NAME] = binding.textInputEdtChangeName.text.toString()
                  dateMap[CHILD_PHONE] = USER.phone
                  dateMap[CHILD_ID] = UID
                  dateMap[CHILD_PHOTO_RESTAURANT] = COMMON.image_restaurant
                  REF_DATABASE_ROOT.child(NODE_ORDERS_CREATE_RESTAURANT).child(UID).updateChildren(dateMap)
                     .addOnCompleteListener {task ->
                        if(task.isSuccessful){
                           showToast(getString(R.string.order_successfully_sent))
                        } else showToast(getString(R.string.something_went_wrong_toast))
                     }
               }
            }
         }
      }
   }

}