package com.example.loginapp.ui.theme.vm

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loginapp.database.DataBase
import com.example.loginapp.database.loginDao
import kotlinx.coroutines.delay

class appViewModel : ViewModel() {

    private val _usuario = MutableLiveData<String>()
    val usuario: LiveData<String> = _usuario

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _loginEnable = MutableLiveData<Boolean>()
    val loginEnable: LiveData<Boolean> = _loginEnable

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    init {

    }

    fun clean(){
        _isLoading.value = false
        _usuario.value = ""
        _password.value = ""
        Log.i("prueba", "user: ${usuario.value}  y pass: ${password.value}")
    }

    fun onLoginChanged(user: String, pass: String) {
        _usuario.value = user
        _password.value = pass
        _loginEnable.value = isValidEmail(user) && isValidPassword(pass)
        Log.i("prueba", "emailv: ${isValidEmail(user)}")
        Log.i("prueba", "passv: ${isValidPassword(pass)}")
        Log.i("prueba", "user: ${usuario.value}  y pass: ${password.value}")
    }

    private fun isValidEmail(email: String): Boolean {
        return if(!email.isEmpty()){
            email === email
        }else{
            false
        }
    }


    private fun isValidPassword(password: String): Boolean = password.length > 6

    suspend fun onLoginSelected() {
        _isLoading.value = true
        delay(4000)
        _isLoading.value = false
    }

}