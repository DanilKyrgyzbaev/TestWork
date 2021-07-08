package com.mobilemirdev.testwork.repository

import com.mobilemirdev.testwork.model.User
import com.mobilemirdev.testwork.network.RetrofitService
import retrofit2.Call

class MainRepository constructor(private val retrofitService: RetrofitService) {

    fun getAllUser() = retrofitService.getAllUser()

    fun createPost(user: User) : Call<User?>? {
        return retrofitService.createPost(user)
    }


//    fun post() = retrofitService.getAllPosts(data = User(1,"",""))

}