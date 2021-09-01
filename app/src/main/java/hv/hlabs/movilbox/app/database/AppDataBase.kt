package hv.hlabs.movilbox.app.database

import androidx.room.Database
import androidx.room.RoomDatabase
import hv.hlabs.movilbox.app.database.dao.PostDao
import hv.hlabs.movilbox.app.database.entities.PostEntity


@Database(entities = [
PostEntity::class
],version=1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun  getPostDao() : PostDao

}