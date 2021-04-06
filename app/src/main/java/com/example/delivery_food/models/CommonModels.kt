package com.example.delivery_food.models


data class CommonModel(
    val id: String = "",
    val name: String = "",
    val phone: String = "",
    val role: String = ""
){
    override fun equals(other: Any?): Boolean {
        return (other as CommonModel).id == id
    }
}