package xyz.siopa.retrofit_app.model.api

import retrofit2.http.GET
import retrofit2.http.Path
import xyz.siopa.retrofit_app.model.Post

interface PostApi{


    @GET("posts/3")
    suspend fun getPost(@Path("id") id:String): Post
}