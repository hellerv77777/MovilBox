package hv.hlabs.movilbox

import android.app.Application
import androidx.room.Room
import hv.hlabs.movilbox.app.database.AppDataBase

class AppApplication : Application() {

    companion object{
        lateinit var database: AppDataBase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            this,
            AppDataBase::class.java,
            "database"
        ).build()
    }
}