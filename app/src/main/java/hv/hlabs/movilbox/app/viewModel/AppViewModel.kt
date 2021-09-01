package hv.hlabs.movilbox.app.viewModel

import androidx.lifecycle.*
import hv.hlabs.movilbox.app.models.AppRepository
import hv.hlabs.movilbox.app.database.entities.PostEntity
import hv.hlabs.movilbox.app.models.UserBean
import kotlinx.coroutines.launch

class AppViewModel() : ViewModel() {

    private val mRepositorio: AppRepository = AppRepository()
    private val response:MutableLiveData<Int> = MutableLiveData();

    fun getPost(type:Int): LiveData<List<PostEntity>> {
        return mRepositorio.getPost(type)
    }

    fun downLoadPost():MutableLiveData<Int> {
       return mRepositorio.downLoadPost()
    }

    fun deleteAllPost() = viewModelScope.launch {
        mRepositorio.deleteAllPost();
    }
    fun deletePost(post: PostEntity) = viewModelScope.launch {
        mRepositorio.deletePost(post);
    }

    fun updatePost(postEntity: PostEntity) = viewModelScope.launch {
        mRepositorio.updatePost(postEntity);
    }

    fun  getDataUser(idUser: Int) :MutableLiveData<UserBean>{
       return mRepositorio.getDataUser(idUser);
    }

}