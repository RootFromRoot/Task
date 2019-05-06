package com.ui.activity

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.app.task.R
import com.data.ID
import com.data.TOKEN
import com.data.model.Data
import com.data.model.Likes
import com.data.util.API
import com.ui.adapter.LikesAdapter
import com.ui.view.MainView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity(), MainView {


    private val requestInterface = API.get()
    lateinit var adapter: LikesAdapter
    var likes: ArrayList<Data> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()


    }

    @SuppressLint("CheckResult")
    override fun setupView() {
        setContentView(R.layout.activity_main)
        actionBar?.title = "Статистика поста"
        Timber.plant(Timber.DebugTree())
        setupAdapter()
        rv_likes_list.layoutManager = LinearLayoutManager(applicationContext)
        rv_likes_list.adapter = adapter
btn_get.setOnClickListener {
        requestInterface.getLikers(ID, TOKEN)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
               adapter.setLikes(ArrayList(it.data))
            }
            }

    }

    override fun setupAdapter() {
        adapter = LikesAdapter(likes)
    }
}


