package com.example.delivery_food.ui.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.delivery_food.R
import com.example.delivery_food.models.Users
import com.example.delivery_food.utilites.*
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.fragment_user_list.*
import kotlinx.android.synthetic.main.users_card.view.*

class UserListFragment : Fragment(R.layout.fragment_user_list) {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: FirebaseRecyclerAdapter<Users, UsersHolder>
    private lateinit var mRefUsers: DatabaseReference

    override fun onStart() {
        super.onStart()
        initRecycler()
    }

    private fun initRecycler() {
        mRecyclerView = users_recycler_view
        mRefUsers = REF_DATABASE_ROOT.child(NODE_USERS).child(UID)
        val options = FirebaseRecyclerOptions.Builder<Users>()
            .setQuery(mRefUsers, Users::class.java)
            .build()

        mAdapter = object : FirebaseRecyclerAdapter<Users, UsersHolder>(options) {

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.users_card, parent, false)
                return UsersHolder(view)
            }

            override fun onBindViewHolder(
                holder: UsersHolder,
                position: Int,
                model: Users,
            ) {
                mRefUsers.addValueEventListener(
                    AppValueEventListener {
                    val users = it.getCommonModel()
                    holder.name.text = users.name
                    holder.role.text = users.role
                })
            }
        }

        mRecyclerView.adapter = mAdapter
        mAdapter.startListening()
    }

    class UsersHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.user_name
        val role: TextView = view.user_role
    }

    override fun onPause() {
        super.onPause()
        mAdapter.stopListening()
    }
}