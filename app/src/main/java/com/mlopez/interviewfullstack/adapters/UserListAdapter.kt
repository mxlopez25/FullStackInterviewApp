package com.mlopez.interviewfullstack.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.mlopez.interviewfullstack.DetailsActivity
import com.mlopez.interviewfullstack.R
import com.mlopez.interviewfullstack.models.Users
import com.mlopez.interviewfullstack.utils.Constants

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
            val intent = Intent(ctx, DetailsActivity::class.java)

            intent.putExtra(Constants.USER_ID_LABEL, Constants.USER_ID)
            intent.putExtra(Constants.USER_ID_VALUE, user.id)

            intent.putExtra(Constants.USER_FIRSTNAME_LABEL , Constants.USER_FIRSTNAME)
            intent.putExtra(Constants.USER_FIRSTNAME_VALUE , user.firstName)

            intent.putExtra(Constants.USER_LASTNAME_LABEL , Constants.USER_LASTNAME)
            intent.putExtra(Constants.USER_LASTNAME_VALUE , user.lastName)

            intent.putExtra(Constants.USER_EMAIL_LABEL , Constants.USER_EMAIL)
            intent.putExtra(Constants.USER_EMAIL_VALUE , user.email)

            intent.putExtra(Constants.USER_USERNAME_LABEL , Constants.USER_USERNAME)
            intent.putExtra(Constants.USER_USERNAME_VALUE , user.username)

            startActivity(ctx, intent, null)
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