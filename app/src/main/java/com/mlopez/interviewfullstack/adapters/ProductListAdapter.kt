package com.mlopez.interviewfullstack.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.mlopez.interviewfullstack.R
import com.mlopez.interviewfullstack.models.Product
import com.mlopez.interviewfullstack.utils.Constants

class ProductListAdapter(private val list: List<Product>): RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {
    private lateinit var ctx: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.product_list_layout, parent, false)
        ctx = parent.context
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = list[position]
        holder.tvProductName.text = product.name

        holder.clProductContainer.setOnClickListener {
            val intent = Intent()

            intent.putExtra(Constants.PRODUCT_ID_LABEL, Constants.PRODUCT_ID)
            intent.putExtra(Constants.PRODUCT_NAME_VALUE, product.id)

            intent.putExtra(Constants.PRODUCT_NAME_LABEL, Constants.PRODUCT_NAME)
            intent.putExtra(Constants.PRODUCT_NAME_VALUE, product.name)

            intent.putExtra(Constants.PRODUCT_SKU_LABEL, Constants.PRODUCT_SKU)
            intent.putExtra(Constants.PRODUCT_SKU_VALUE, product.sku)

            intent.putExtra(Constants.PRODUCT_PRICE_LABEL, Constants.PRODUCT_PRICE)
            intent.putExtra(Constants.PRODUCT_PRICE_VALUE, product.price)
        }
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvProductName: TextView = itemView.findViewById(R.id.tvProductName)
        val clProductContainer: ConstraintLayout = itemView.findViewById(R.id.clProductContainer)
    }
}