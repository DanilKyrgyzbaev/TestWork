package com.mobilemirdev.testwork

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mobilemirdev.testwork.model.User
import com.mobilemirdev.testwork.repository.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel  constructor(private val repository: MainRepository)  : ViewModel() {

    val userList = MutableLiveData<List<User>>()
    val userModel = MutableLiveData<User>()
    val errorMessage = MutableLiveData<String>()

    fun getAllUser() {

        val response = repository.getAllUser()
        response.enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                userList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }

    fun createPost(user: User) {

        val response = repository.createPost(user)
        response!!.enqueue(object : Callback<User?>{
            override fun onResponse(call: Call<User?>, response: Response<User?>) {
                userModel.postValue(response.body())
            }

            override fun onFailure(call: Call<User?>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }

    fun getPostUser(){
//        val responsePost = repository.post()

    }
}
