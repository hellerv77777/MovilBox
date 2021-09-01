package hv.hlabs.movilbox.app.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "post")
data class PostEntity(
    @PrimaryKey
    val id:Int,
    val userId:Int,
    val title:String,
    val body:String,
    var favorite:Int,
    var leido:Int
)
