@file:Suppress("UNUSED_CHANGED_VALUE")

package com.example.delivery_food.utilites

import android.net.Uri
import com.example.delivery_food.models.Address
import com.example.delivery_food.models.CommonModel
import com.example.delivery_food.models.Restaurant
import com.example.delivery_food.models.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

lateinit var AUTH: FirebaseAuth
lateinit var UID: String
lateinit var REF_DATABASE_ROOT: DatabaseReference
lateinit var REF_STORAGE_ROOT: StorageReference
lateinit var USER: Users
lateinit var ADDRESS: Address
lateinit var RESTAURANT: Restaurant
lateinit var COMMON: CommonModel

const val FOLDER_PHOTO_RESTAURANT = "photo_restaurant"

//User const
const val NODE_USERS = "users"
const val NODE_ADDRESS = "address"
const val NODE_RESTAURANT = "restaurant"
const val NODE_ORDERS_CREATE_RESTAURANT = "orders_create_restaurant"
const val CHILD_PHONE = "phone"
const val CHILD_ROLE = "role"
const val CHILD_NAME = "name"
const val CHILD_ID = "uid"
const val CHILD_PHOTO_RESTAURANT = "image_restaurant"

//Address const
const val CHILD_HOUSE = "house"
const val CHILD_ENTRY = "entry"
const val CHILD_FLOOR = "floor"
const val CHILD_FLAT = "flat"

//Role const
const val USER_ROLE = "user"
const val ADMIN_ROLE = "admin"
const val RESTAURANTS_ROLE = "restaurants"

//Initial firebase help function
fun initFirebase() {
    AUTH = FirebaseAuth.getInstance()
    REF_DATABASE_ROOT = FirebaseDatabase.getInstance().reference
    REF_STORAGE_ROOT = FirebaseStorage.getInstance().reference
    COMMON = CommonModel()
    USER = Users()
    ADDRESS = Address()
    RESTAURANT = Restaurant()
    UID = AUTH.currentUser?.uid.toString()
}

fun DataSnapshot.getCommonModel(): CommonModel =
    this.getValue(CommonModel::class.java) ?: CommonModel()

//Initial users
fun initUser() {
    REF_DATABASE_ROOT.child(NODE_USERS).child(UID)
        .addListenerForSingleValueEvent(AppValueEventListener {
            USER = it.getValue(Users::class.java) ?: Users()
        })
}

//Initial address
fun initAddress() {
    REF_DATABASE_ROOT.child(NODE_ADDRESS).child(UID)
        .addListenerForSingleValueEvent(AppValueEventListener {
            ADDRESS = it.getValue(Address::class.java) ?: Address()
        })
}

//Initial restaurant
fun initRestaurant() {
    REF_DATABASE_ROOT.child(NODE_RESTAURANT).child(UID)
        .addListenerForSingleValueEvent(AppValueEventListener {
            RESTAURANT = it.getValue(Restaurant::class.java) ?: Restaurant()
        })
}

//Get URL image in storage
inline fun getUrlFromStorage(path: StorageReference, crossinline function: (url: String) -> Unit) {
    path.downloadUrl
        .addOnSuccessListener { function(it.toString()) }
        .addOnFailureListener { showToast(it.message.toString()) }
}

//Send image in storage
inline fun putFileToStorage(uri: Uri, path: StorageReference, crossinline function: () -> Unit) {
    path.putFile(uri)
        .addOnSuccessListener { function() }
        .addOnFailureListener { showToast(it.message.toString()) }
}

//Send URL in realtime database
inline fun putUrlToDatabase(url: String, crossinline function: () -> Unit) {
    REF_DATABASE_ROOT.child(NODE_ORDERS_CREATE_RESTAURANT).child(UID)
        .child(FOLDER_PHOTO_RESTAURANT).setValue(url)
        .addOnSuccessListener { function() }
        .addOnFailureListener { showToast(it.message.toString()) }
}
