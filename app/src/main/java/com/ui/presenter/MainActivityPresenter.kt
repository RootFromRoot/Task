package com.ui.presenter

import com.data.ID
import com.data.TOKEN
import com.data.util.API
import com.ui.view.MainView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface MainActivityPresenter {
    fun getLikesFromServer()
    fun getCommentsFromServer()
    fun getRepostsFromServer()
    fun getMentionFromServer()
    var view: MainView
    var requestInterface: API

    fun bind(view: MainView) {
        this.view = view
    }
}

class MainActivityPresenterImpl : MainActivityPresenter {
    override lateinit var view: MainView
    override var requestInterface = API.get()

    override fun getCommentsFromServer() {
        requestInterface.getComments(ID, TOKEN)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                view.activity.CommentsAdapter.setComments(ArrayList(it.data))
            }
    }

    override fun getRepostsFromServer() {
        requestInterface.getReposts(ID, TOKEN)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                view.activity.repostsAdapter.setReposts(ArrayList(it.data))
            }
    }

    override fun getMentionFromServer() {
        requestInterface.getMentions(ID, TOKEN)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                view.activity.mentionAdapter.setMention(ArrayList(it.data))
            }
    }

    override fun getLikesFromServer() {
        requestInterface.getLikers(ID, TOKEN)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                view.activity.adapter.setLikes(ArrayList(it.data))

            }

    }
}