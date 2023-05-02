package com.don.fitpeo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.don.fitpeo.R
import com.don.fitpeo.models.ResponseItem
import com.squareup.picasso.Picasso

class CustomAdapter(
    private val mList: List<ResponseItem>,
    private val itemActionListener: ItemActionListener
) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the item_view_row view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view_row, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val itemsViewModel = mList[position]
        Picasso.get().load(itemsViewModel.url).into(holder.imageView);

        holder.imageView.setOnClickListener{
            itemActionListener.onClickItem(itemsViewModel)
        }

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)

    }
}

interface ItemActionListener {
    fun onClickItem(responseItem: ResponseItem)
}