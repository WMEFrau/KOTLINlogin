package com.example.loginapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Solicitud::class , Login::class] , version = 1)
abstract class DataBase : RoomDatabase() {
    abstract fun LoginDao() : loginDao

    companion object {
        @Volatile
        private var BASE_DATOS : DataBase? = null
        const val DB_NOMBRE = "iplabank.db"

        fun getInstance (contexto : Context) : DataBase {
            return BASE_DATOS ?: synchronized(this) {
                Room.databaseBuilder(
                    contexto.applicationContext,
                    DataBase::class.java,
                    DB_NOMBRE
                ).fallbackToDestructiveMigration().build().also { BASE_DATOS = it }
            }
        }
    }


}