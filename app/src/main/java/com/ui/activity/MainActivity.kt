package com.ui.activity

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.app.task.R
import com.data.ID
import com.data.SLUG
import com.data.TOKEN
import com.data.model.Data
import com.data.model.Likes
import com.data.util.API
import com.ui.adapter.*
import com.ui.presenter.MainActivityPresenter
import com.ui.presenter.MainActivityPresenterImpl

import com.ui.view.MainView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity(), MainView {
    override var activity: MainActivity = this
    private val presenter: MainActivityPresenter = MainActivityPresenterImpl()

    lateinit var adapter: LikesAdapter
    lateinit var repostsAdapter: RepostsAdapret
    lateinit var mentionAdapter: MentionAdapter
    lateinit var CommentsAdapter: CommentsAdapter

    var likes: ArrayList<Data> = ArrayList()
    var comments: ArrayList<Data> = ArrayList()
    var reposts: ArrayList<Data> = ArrayList()
    var mentions: ArrayList<Data> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()

        presenter.bind(this)
    }

    @SuppressLint("CheckResult", "SetTextI18n")
    override fun setupView() {
        setContentView(R.layout.activity_main)

        Timber.plant(Timber.DebugTree())
        setupAdapter()
        
        presenter.getLikesFromServer()
        presenter.getCommentsFromServer()
        presenter.getRepostsFromServer()
        presenter.getMentionFromServer()

        presenter.requestInterface.getPost(SLUG, TOKEN)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                tv_likes_count.text = "Лайки " + it.likes_count
                tv_comments_count.text = "Комментаторы " + it.comments_count
                tv_bookmarks_count.text = "Закладки " + it.bookmarks_count
                tv_mentions_count.text = "Отметки " + it.author.avatar_image.mentioned_users_count
                tv_reposts_count.text = "Репосты " + it.reposts_count
                tv_viewers_count.text = "Просмотры " + it.views_count
            }
    }

    override fun setupAdapter() {
        setupLayoutManagers()

        adapter = LikesAdapter(likes)
        CommentsAdapter = CommentsAdapter(comments)
        repostsAdapter = RepostsAdapret(reposts)
        mentionAdapter = MentionAdapter(mentions)

        rv_likes_list.adapter = adapter
        rv_comments_list.adapter = mentionAdapter
        rv_reposts_list.adapter = repostsAdapter
        rv_mentions_list.adapter = mentionAdapter

    }

    override fun setupLayoutManagers() {
        rv_likes_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_comments_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_reposts_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_mentions_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }
}


