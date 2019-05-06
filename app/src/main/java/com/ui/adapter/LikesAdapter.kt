package com.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.task.R
import com.data.model.Data
import com.data.model.Likes
import com.ui.holder.LikesViewHolder

class LikesAdapter(var items: ArrayList<Data>): RecyclerView.Adapter<LikesViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikesViewHolder {
        return LikesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_likes, parent, false))
    }

    fun setLikes(likes: ArrayList<Data>) {
        this.items = likes
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: LikesViewHolder, position: Int) {
        holder.nick.text = items[position].nickname
    }

    override fun getItemCount(): Int {
        return items.size
    }
}