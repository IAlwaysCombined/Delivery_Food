@file:Suppress("UNUSED_CHANGED_VALUE")

package com.example.delivery_food.utilites

import com.example.delivery_food.models.Address
import com.example.delivery_food.models.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

lateinit var AUTH: FirebaseAuth
lateinit var UID: String
lateinit var REF_DATABASE_ROOT: DatabaseReference
lateinit var USER: Users
lateinit var ADDRESS: Address

//User const
const val NODE_USERS = "users"
const val NODE_ADDRESS = "address"
const val CHILD_PHONE = "phone"
const val CHILD_ROLE = "role"
const val CHILD_NAME = "name"

//Address const
const val CHILD_HOUSE = "house"
const val CHILD_ENTRY = "entry"
const val CHILD_FLOOR = "floor"
const val CHILD_FLAT = "flat"
const val CHILD_ID_ADDRESS = "id_address"

//Role const
const val USER_ROLE = "user"
const val ADMIN_ROLE = "admin"

//Initial firebase help function
fun initFirebase() {
    AUTH = FirebaseAuth.getInstance()
    REF_DATABASE_ROOT = FirebaseDatabase.getInstance().reference
    USER = Users()
    ADDRESS = Address()
    UID = AUTH.currentUser?.uid.toString()
}