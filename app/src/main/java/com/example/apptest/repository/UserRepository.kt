package com.example.apptest.repository

import com.example.apptest.model.LoginRequest
import com.example.apptest.model.LoginResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import rest.ApiService
import javax.inject.Inject

class UserRepository @Inject constructor(var userApiService: ApiService) {

    fun login(emial: String, password: String): Single<LoginResponse> {
        return userApiService.login(LoginRequest(emial, password))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}