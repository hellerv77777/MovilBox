package hv.hlabs.movilbox.app.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import hv.hlabs.movilbox.app.database.entities.PostEntity

@Dao
interface PostDao {

    @Query("SELECT * FROM post")
    fun getAllPost(): LiveData<List<PostEntity>>

    @Query("SELECT * FROM post WHERE favorite = '1'")
    fun getFavoritePost(): LiveData<List<PostEntity>>

    @Query("DELETE FROM POST")
    suspend fun deleteAllPost()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updatePost(postEntity: PostEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPost(body: List<PostEntity?>?)

    @Delete
    suspend fun deletePost(post: PostEntity)
}