package hv.hlabs.movilbox.app.models

import android.app.Application
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import hv.hlabs.movilbox.AppApplication
import hv.hlabs.movilbox.app.database.AppDataBase
import hv.hlabs.movilbox.app.database.entities.PostEntity
import hv.hlabs.movilbox.app.models.UserBean
import hv.hlabs.movilbox.app.services.RetrofitClient
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class AppRepository()  {

    val dataBase = AppApplication.database

    val postDao = dataBase.getPostDao()

    fun getPost(type:Int) : LiveData<List<PostEntity>> {

        return if(type==0){
            postDao.getAllPost()
        }else{
            postDao.getFavoritePost()
        }
    }

    private fun insertPosts(body: List<PostEntity>?) {
        GlobalScope.launch {
            postDao.insertPost(body)
        }
    }

    suspend fun updatePost(postEntity: PostEntity) {
        postDao.updatePost(postEntity)
    }

    suspend fun deleteAllPost() {
        postDao.deleteAllPost()
    }

    suspend fun deletePost(post: PostEntity) {
        postDao.deletePost(post)
    }

    //TODO Consumo servicios

    fun getDataUser(idUser: Int): MutableLiveData<UserBean> {

        val resp : MutableLiveData<UserBean> = MutableLiveData();

        RetrofitClient.apiServices.getUser(idUser).enqueue(object :Callback<UserBean>{
            override fun onResponse(call: Call<UserBean>, response: Response<UserBean>) {

                if(response.isSuccessful && response.body()!=null){
                    resp.postValue(response.body())
                }else{
                    resp.postValue(null)
                }
            }

            override fun onFailure(call: Call<UserBean>, t: Throwable) {
                resp.postValue(null)
            }
        })

        return resp
    }

    fun downLoadPost() : MutableLiveData<Int>{

        val resp : MutableLiveData<Int> = MutableLiveData();

        RetrofitClient.apiServices.getPost().enqueue(object: Callback<List<PostEntity>> {
            override fun onResponse(call: Call<List<PostEntity>>, response: Response<List<PostEntity>>) {

                if(response.isSuccessful){
                    insertPosts(response.body())
                    resp.postValue(200)
                }else{
                    resp.postValue(500)
                }
            }
            override fun onFailure(call: Call<List<PostEntity>>, t: Throwable) {
                resp.postValue(500)
            }
        })

        return resp;
    }




}