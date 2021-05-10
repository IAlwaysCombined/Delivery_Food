package com.example.delivery_food.models


data class CommonModel(
    val uid: String = "",
    val name: String = "",
    val phone: String = "",
    val role: String = "",
    var image_restaurant: String = "",

    val name_dishes: String = "",
    val coast_dishes: String = "",
    val coast_order: String = "",
    var image_dishes: String = "",
    val number: String = "",
    val uid_dishes: String = "",

    var count: String = "",
){
    override fun equals(other: Any?): Boolean {
        return (other as CommonModel).uid == uid
    }
}