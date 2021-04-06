package com.example.delivery_food.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.delivery_food.R
import com.example.delivery_food.models.CommonModel

class UserListAdapter(private var mUserList: MutableList<CommonModel> = mutableListOf()): RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView? = itemView.findViewById(R.id.user_name)
        var role: TextView? = itemView.findViewById(R.id.user_role)
    }

    override fun getItemCount() = mUserList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.users_card, parent, false)
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.name?.text = mUserList[position].name
        holder.role?.text = mUserList[position].role
    }

    fun setList(list: List<CommonModel>) {
        mUserList = list as MutableList<CommonModel>
        notifyDataSetChanged()
    }
}



