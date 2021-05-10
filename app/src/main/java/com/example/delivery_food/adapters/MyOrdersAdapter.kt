package com.example.delivery_food.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.delivery_food.R
import com.example.delivery_food.models.CommonModel

class MyOrdersAdapter(private var myOrderList: MutableList<CommonModel> = mutableListOf()): RecyclerView.Adapter<MyOrdersAdapter.MyOrderHolder>() {

    class MyOrderHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val coastOrder: TextView? = itemView.findViewById(R.id.coast_order)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyOrderHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_my_order, parent, false)
        return MyOrdersAdapter.MyOrderHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyOrderHolder, position: Int) {
        holder.coastOrder?.text = "Цена заказа: " + myOrderList[position].coast_order
    }

    override fun getItemCount() = myOrderList.size

    fun setList(list: List<CommonModel>) {
        myOrderList = list as MutableList<CommonModel>
        notifyDataSetChanged()
    }
}