package com.example.delivery_food.utilites

import com.example.delivery_food.models.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

lateinit var AUTH: FirebaseAuth
lateinit var UID: String
lateinit var REF_DATABASE_ROOT: DatabaseReference
lateinit var USER: Users
lateinit var EMAIL: String

const val NODE_USERS = "users"
const val CHILD_PHONE = "phone"
const val CHILD_ROLE = "role"
const val CHILD_NAME = "name"

fun initFirebase() {
    AUTH = FirebaseAuth.getInstance()
    REF_DATABASE_ROOT = FirebaseDatabase.getInstance().reference
    USER = Users()
    UID = AUTH.currentUser?.uid.toString()
}