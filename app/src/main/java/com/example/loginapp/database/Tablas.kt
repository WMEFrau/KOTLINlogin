package com.example.loginapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.sql.Date


@Entity(tableName = "solicitud")
data class Solicitud(
    @PrimaryKey(autoGenerate = false) val rut: String,
    @ColumnInfo(name = "nombre_completo") val nombre: String,
    @ColumnInfo(name = "fecha_nacimiento") val fechaNacimiento: String,
    val email: String,
    val telefono: String,
    @ColumnInfo(name = "c_frontal") var cFrontal: String,
    @ColumnInfo(name = "c_trasera") var cTrasera: String,
    var latitud: String,
    var longitud: String
)

@Entity(
    tableName = "login"
)
data class Login(
    @PrimaryKey(autoGenerate = false) val user: String,
    var password: String,
    @ColumnInfo(name = "solicitud_rut") val solicitudRut: String
)