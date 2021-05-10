package com.example.delivery_food.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.delivery_food.R
import com.example.delivery_food.models.CommonModel
import com.example.delivery_food.utilites.*

@Suppress("UNUSED_CHANGED_VALUE")
class DishesListAdapter(private var dishesList: MutableList<CommonModel> = mutableListOf()) :
    RecyclerView.Adapter<DishesListAdapter.DishesListHolder>() {

    class DishesListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameDishes: TextView? = itemView.findViewById(R.id.name_dishes)
        var coastDishes: TextView? = itemView.findViewById(R.id.coast_dishes)
        var imageDishes: ImageView? = itemView.findViewById(R.id.image_dishes)
        val addBtn: Button? = itemView.findViewById(R.id.add_count_dishes)
        val deleteBtn: Button? = itemView.findViewById(R.id.delete_count_dishes)
        val cont: TextView? = itemView.findViewById(R.id.count_dishes)
        val addDishes: Button? = itemView.findViewById(R.id.add_dishes)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishesListHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_restaurant_dishes, parent, false)
        return DishesListAdapter.DishesListHolder(itemView)
    }

    override fun onBindViewHolder(holder: DishesListHolder, position: Int) {
        holder.nameDishes?.text = dishesList[position].name_dishes
        holder.coastDishes?.text = dishesList[position].coast_dishes
        holder.imageDishes?.downloadAndSetImage(dishesList[position].image_dishes)
        holder.cont?.text = "1"
        holder.addBtn?.setOnClickListener {
            var count = holder.cont?.text.toString().toInt()
            count++
            holder.cont?.text = count.toString()
            dishesList[position].count = count.toString()
        }
        holder.deleteBtn?.setOnClickListener {
            var count = holder.cont?.text.toString().toInt()
            if (count <= 0) {
                showToast("Вы удалили все товары")
            } else {
                count--
                holder.cont?.text = count.toString()
            }
        }

        holder.addDishes?.setOnClickListener {
            val dateMap = mutableMapOf<String, Any>()
            dateMap[CHILD_COUNT] = dishesList[position].count
            dateMap[CHILD_NAME] = holder.nameDishes?.text.toString()
            val uidDishes = REF_DATABASE_ROOT.child(NODE_ORDER).child(UID).push().key.toString()
            REF_DATABASE_ROOT.child(NODE_BASKET).child(UID).child(uidDishes).updateChildren(dateMap)
        }
    }

    override fun getItemCount() = dishesList.size


    fun setList(list: List<CommonModel>) {
        dishesList = list as MutableList<CommonModel>
        notifyDataSetChanged()
    }
}