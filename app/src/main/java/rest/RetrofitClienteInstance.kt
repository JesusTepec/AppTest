package rest

import android.content.Context
import com.readystatesoftware.chuck.ChuckInterceptor
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory




class RetrofitClienteInstance {

    companion object {

        private var retrofit: Retrofit? = null
        private const val url = "https://pokeapi.co/api/v2/"

        fun getInstance(context: Context): Retrofit {
            val client = OkHttpClient.Builder()
                .addInterceptor(ChuckInterceptor(context))
                .build()
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(url)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build()
            }
            return retrofit!!

        }
    }

}