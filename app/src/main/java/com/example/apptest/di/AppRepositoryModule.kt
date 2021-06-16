package di

import android.content.Context
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import com.example.apptest.repository.UserRepository
import rest.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppRepositoryModule(var context: Context) {

    @Provides
    @Singleton
    fun provideHttpClient() = OkHttpClient.Builder()
        .addInterceptor(ChuckInterceptor(context))
        .build()

    @Provides
    @Singleton
    fun provideApiService(client: OkHttpClient) = Retrofit.Builder()
        .baseUrl("https://api.finerio.mx/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .client(client)
        .build()
        .create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideApiRepository(apiService: ApiService) = UserRepository(apiService)

}