package com.data.util

import com.data.TOKEN
import com.data.model.Comments
import com.data.model.Likes
import com.data.model.Post
import com.data.model.Reposts
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import timber.log.Timber

interface API {
    @GET("get")
    fun getPost(
        @Query("slug") slug: String,
        @Header("Authorization") token: String
    ): Observable<Post>

    @GET("likers/all")
    fun getLikers(
        @Query("id") id: String,
        @Header("Authorization") token: String
    ): Observable<Likes>

    @GET("reposters/all")
    fun getReposts(
        @Query("id") slug: String,
        @Header("Authorization") token: String
    ): Observable<Reposts>

    @GET("commentators/all")
    fun getComments(
        @Query("id") slug: String,
        @Header("Authorization") token: String
    ): Observable<Comments>

    @GET("mentions/all")
    fun getMentions(
        @Query("id") slug: String,
        @Header("Authorization") token: String
    ): Observable<Comments>

    companion object {
        private val retrofit: Retrofit = Retrofit.Builder()
            .client(OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor(HttpLoggingInterceptor.Logger
                { message -> Timber.i(message) }).apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()
            )
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.inrating.top/v1/users/posts/")
            .build()

        fun get(): API {
            return retrofit.create(API::class.java)
        }
    }
}