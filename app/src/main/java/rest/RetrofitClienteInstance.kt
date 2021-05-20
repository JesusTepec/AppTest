package rest

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory




class RetrofitClienteInstance {

    companion object {

        private var retrofit: Retrofit? = null
        private val url = "https://6060fd02ac47190017a70602.mockapi.io/api/"

        fun getInstance(): Retrofit {
            val client = OkHttpClient.Builder().build()

            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(url)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!

        }
    }

}