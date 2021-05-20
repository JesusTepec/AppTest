package rest

import model.ResponseTransations
import retrofit2.Call
import retrofit2.http.GET

interface RestApi {

    @GET("transactions")
    fun getTransactions(): Call<ResponseTransations>

}