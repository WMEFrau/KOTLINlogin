package com.example.loginapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import java.sql.Date

@Dao
interface loginDao {
    @Query("SELECT user FROM login WHERE user = :user")
    suspend fun getUser(user: String): String

    @Query("SELECT password FROM login WHERE password = :pass")
    suspend fun getPass(pass: String): String

    @Query("SELECT rut FROM solicitud WHERE rut = :rut")
    suspend fun getSolicitud(rut: String): String

    @Insert
    suspend fun addSolicitud(solicitud: Solicitud): Long

    suspend fun Test() {
        var solicitud = Solicitud(
            rut =  "17.051.158-1", nombre = "Francisco Jerez", fechaNacimiento =  Date(19890119),
        email =  "fran@gmail.com", telefono = "56932165487" ,  cFrontal =  "", cTrasera= "", latitud= "", longitud = ""
        )
        addSolicitud(solicitud)
    }

}