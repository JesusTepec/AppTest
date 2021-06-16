package com.example.apptest.repository

import android.util.Log
import rest.ApiService
import javax.inject.Inject

class UserRepository @Inject constructor(var userApiService: ApiService) {

    fun login() {
        userApiService.login()

        Log.d("Repository", "Repository method")
    }

}