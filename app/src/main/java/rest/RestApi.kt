package rest

import android.content.Context
import io.reactivex.rxjava3.core.Single
import model.PokemonResponse
import retrofit2.http.GET

interface RestApi {

    @GET("pokemon")
    fun getPokemons(): Single<PokemonResponse>

    companion object {
        fun getService(context: Context): RestApi {
            return RetrofitClienteInstance.getInstance(context).create(RestApi::class.java)
        }
    }
}