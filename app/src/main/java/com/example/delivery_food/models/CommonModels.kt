package com.example.delivery_food.models


data class CommonModel(
    val uid: String = "",
    val name: String = "",
    val phone: String = "",
    val role: String = "",
){
    override fun equals(other: Any?): Boolean {
        return (other as CommonModel).uid == uid
    }
}