package com.example.delivery_food.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.example.delivery_food.R
import com.example.delivery_food.models.CommonModel
import com.example.delivery_food.ui.fragment.screens.user.DishesListFragment
import com.example.delivery_food.utilites.*


class RestaurantListAdapter(private var mRestaurantList: MutableList<CommonModel> = mutableListOf()):
    RecyclerView.Adapter<RestaurantListAdapter.RestaurantListViewHolder>() {

    class RestaurantListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameRestaurant: TextView? = itemView.findViewById(R.id.restaurant_name)
        var restaurantImage: ImageView? = itemView.findViewById(R.id.restaurants_image)
        val restaurantInfo: ImageView? = itemView.findViewById(R.id.restaurants_image)
    }

    override fun getItemCount() = mRestaurantList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantListViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_restaurants, parent, false)
        return RestaurantListAdapter.RestaurantListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RestaurantListViewHolder, position: Int) {
        holder.nameRestaurant?.text = mRestaurantList[position].name
        holder.restaurantImage?.downloadAndSetImage(mRestaurantList[position].image_restaurant)
        holder.restaurantInfo?.setOnClickListener {

            val bundle = Bundle()
            bundle.putString("nameRestaurant", mRestaurantList[position].name)
            bundle.putString("uidRestaurant", mRestaurantList[position].uid)
            println(mRestaurantList[position].uid)

            val frgFav = DishesListFragment(bundle)
            frgFav.arguments = bundle

            val fragmentTrasaction: FragmentTransaction = APP_ACTIVITY.supportFragmentManager.beginTransaction()
            fragmentTrasaction.replace(R.id.main_container, frgFav, "frg")
            fragmentTrasaction.commit()

        }
    }

    fun setList(list: List<CommonModel>) {
        mRestaurantList = list as MutableList<CommonModel>
        notifyDataSetChanged()
    }
}