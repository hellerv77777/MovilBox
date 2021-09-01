package hv.hlabs.movilbox.app.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL : String = "https://jsonplaceholder.typicode.com//"
    val apiServices: ApiServices by lazy{

        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()

        retrofit.create(ApiServices::class.java)
    }

}