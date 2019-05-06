package com.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.task.R
import com.data.model.Data
import com.squareup.picasso.Picasso
import com.ui.holder.LikesViewHolder

class MentionAdapter(var items: ArrayList<Data>): RecyclerView.Adapter<LikesViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikesViewHolder {
        return LikesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_mentions, parent, false))
    }

    fun setMention(likes: ArrayList<Data>) {
        this.items = likes
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: LikesViewHolder, position: Int) {
        holder.nick.text = items[position].nickname
        Picasso.get().load(items[position].avatar_image.url_medium).into(holder.avatar)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}