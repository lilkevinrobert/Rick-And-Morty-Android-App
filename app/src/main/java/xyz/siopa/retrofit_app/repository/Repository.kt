package xyz.siopa.retrofit_app.repository

import xyz.siopa.retrofit_app.model.Post
import xyz.siopa.retrofit_app.utils.RetrofitInstance

class Repository {

    suspend fun getPost(): Post{
        //Get post
        return RetrofitInstance.api.getPost("1");
    }
}