package rest

import com.example.apptest.model.LoginResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface ApiService {

    @GET("login")
    fun login(): Single<LoginResponse>

}