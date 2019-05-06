package com.ui.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_list_likes.view.*

class LikesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var nick = view.tv_nick!!
    val avatar = view.iv_avatar}