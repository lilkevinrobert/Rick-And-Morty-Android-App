package xyz.siopa.retrofit_app.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import xyz.siopa.retrofit_app.model.api.PostApi
import xyz.siopa.retrofit_app.utils.Constants.Companion.BASE_URL

object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: PostApi by lazy {
        retrofit.create(PostApi::class.java)
    }

}