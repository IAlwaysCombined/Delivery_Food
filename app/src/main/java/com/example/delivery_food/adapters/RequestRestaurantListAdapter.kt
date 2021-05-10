package com.example.delivery_food.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.delivery_food.R
import com.example.delivery_food.models.CommonModel
import com.example.delivery_food.utilites.*

class RequestRestaurantListAdapter(private var mRestaurantRequestList: MutableList<CommonModel> = mutableListOf()) :
    RecyclerView.Adapter<RequestRestaurantListAdapter.RestaurantViewHolder>() {

    class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameRestaurant: TextView? = itemView.findViewById(R.id.list_restaurant_name_text_view)
        var addRestaurant: Button? = itemView.findViewById(R.id.add_restaurant_btn)
        var deleteRequestRestaurant: Button? = itemView.findViewById(R.id.delete_request_restaurant)
    }

    override fun getItemCount() = mRestaurantRequestList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_request_create_restaurants, parent, false)
        return RestaurantViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        holder.nameRestaurant?.text = mRestaurantRequestList[position].name
        holder.addRestaurant?.setOnClickListener {
            val dateMap = mutableMapOf<String, Any>()
            dateMap[CHILD_ROLE] = RESTAURANTS_ROLE
            dateMap[CHILD_NAME] = mRestaurantRequestList[position].name
            dateMap[CHILD_PHONE] = mRestaurantRequestList[position].phone
            dateMap[CHILD_PHOTO_RESTAURANT] = mRestaurantRequestList[position].image_restaurant
            REF_DATABASE_ROOT.child(NODE_RESTAURANT).child(mRestaurantRequestList[position].uid).updateChildren(dateMap)
            showToast("Ресторан добавлен!")
            REF_DATABASE_ROOT.child(NODE_ORDERS_CREATE_RESTAURANT).child(mRestaurantRequestList[position].uid).removeValue()
            REF_DATABASE_ROOT.child(NODE_USERS).child(mRestaurantRequestList[position].uid).removeValue()
        }
        holder.deleteRequestRestaurant?.setOnClickListener {
            REF_DATABASE_ROOT.child(
                NODE_ORDERS_CREATE_RESTAURANT
            ).child(mRestaurantRequestList[position].uid).removeValue()
            showToast("Заявка на создание ресторана отклонена!")
        }
    }

    fun setList(list: List<CommonModel>) {
        mRestaurantRequestList = list as MutableList<CommonModel>
        notifyDataSetChanged()
    }
}