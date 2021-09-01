package hv.hlabs.movilbox.app.services

import hv.hlabs.movilbox.app.database.entities.PostEntity
import hv.hlabs.movilbox.app.models.UserBean
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServices {

    @GET("/posts")
    fun getPost(): Call<List<PostEntity>>

    @GET("/users/{idUser}")
    fun getUser(@Path("idUser") idUser:Int ) : Call<UserBean>
}