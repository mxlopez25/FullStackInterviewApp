package com.mlopez.interviewfullstack.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.mlopez.interviewfullstack.R
import com.mlopez.interviewfullstack.models.Users

class UserListAdapter(private val list: List<Users>): RecyclerView.Adapter<UserListAdapter.ViewHolder>() {
    private lateinit var ctx: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.user_list_layout, parent, false)
        ctx = parent.context
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: UserListAdapter.ViewHolder, position: Int) {
        val user = list[position]
        holder.tvName.text = "${user.firstName} ${user.lastName}"

        holder.clContainer.setOnClickListener {
            Toast.makeText(ctx, "${user.firstName} ${user.lastName}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val clContainer: ConstraintLayout = itemView.findViewById(R.id.clContainer)
    }
}