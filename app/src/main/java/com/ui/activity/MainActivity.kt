package com.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.app.task.R
import com.data.ID
import com.data.SLUG
import com.data.TOKEN
import com.data.util.API
import com.ui.view.MainView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity(), MainView {
    private val requestInterface = API.get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()

    }

    override fun setupView() {
        setContentView(R.layout.activity_main)
        Timber.plant(Timber.DebugTree())

        get.setOnClickListener {
            requestInterface.getLikers(ID, TOKEN)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Timber.i(it.data.forEach {
                        Timber.i(it.nickname)
                        Timber.i(it.avatar_image.url_medium)
                    }.toString())

                }

            requestInterface.getPost(SLUG, TOKEN)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Timber.i(it.likes_count)
                }
        }
    }
}
