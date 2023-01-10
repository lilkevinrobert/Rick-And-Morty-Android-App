package xyz.siopa.retrofit_app

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("/posts/")
    fun getPost(@Query("id") id: Int):Call<Any>
}