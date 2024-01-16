package com.example.loginapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.loginapp.ui.theme.LoginAppTheme
import com.example.loginapp.ui.theme.btnColor
import com.example.loginapp.ui.theme.vm.appViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val vm by viewModels<appViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    appStart()
                }
            }
        }
    }
}

@Composable
fun appStart(navController: NavHostController = rememberNavController() ) {
    NavHost(
        navController = navController,
        startDestination = "login"
    ) {

        composable("login") {
            Login(
                onButtonSolicitar = {
                    navController.navigate("solicitud")
                }
            )
        }
        composable("solicitud") {
            Registro(
                onBackButtonClicked = {
                    navController.popBackStack()
                }

            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true, locale = "es")
@Composable
fun Login(onButtonSolicitar: () -> Unit = {}, vm: appViewModel = viewModel()) {

    val usuario: String by vm.usuario.observeAsState(initial = "")
    val password: String by vm.password.observeAsState(initial = "")
    val loginEnable: Boolean by vm.loginEnable.observeAsState(initial = false)
    val isLoading: Boolean by vm.isLoading.observeAsState(initial = false)

    val coroutineScope = rememberCoroutineScope()

    val phUser = "Usuario"
    val phPassword = "Contraseña"
    val (tfUser, fUser) = rememberSaveable {
        mutableStateOf("")
    }

    val (tfPassword, fPassword) = rememberSaveable {
        mutableStateOf("")
    }

    Box(
        contentAlignment = Alignment.Center, modifier = Modifier
            .fillMaxWidth()
    ) {

        if (isLoading) {
            Box(Modifier.fillMaxSize()) {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }
        }
        else{
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(

                    text = "IplaBank", fontWeight = FontWeight.Bold, fontSize = 30.sp
                )
                TextField(
                    value = usuario,
                    onValueChange = { vm.onLoginChanged(it, password) },
                    placeholder = {
                        Text(phUser)
                    },
                    singleLine = true,
                    maxLines = 1
                )
                TextField(value = password,
                    onValueChange = { vm.onLoginChanged(usuario, it) },
                    placeholder = {
                        Text(phPassword)
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    singleLine = true,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(15.dp))
                btnIngresar(loginEnable){
                    coroutineScope.launch {
                        vm.onLoginSelected()
                    }
                }
                Button(
                    onClick = { onButtonSolicitar() },
                    colors = ButtonDefaults.buttonColors(containerColor = btnColor)
                ) {

                    Text(text = "Solicitar cuenta")
                }
            }
        }

    }
}

@Composable
fun btnIngresar(loginEnable:Boolean, onLoginSelected: () -> Unit){
    Button(
        onClick = { onLoginSelected() },
        colors = ButtonDefaults.buttonColors(containerColor = btnColor)
        , enabled = loginEnable
    ) {
        Text(text = "Ingresar")
    }
}

@Preview(showSystemUi = true, locale = "es")
@Composable
fun Registro(onBackButtonClicked: () -> Unit = {}) {

    val phNombre = "Nombre Completo"
    val phRut = "RUT"
    val phNacimiento = "Fecha Nacimiento"
    val phEmail = "Email"
    val phTelefono = "Teléfono"

    val (tfNombre, fNombre) = rememberSaveable {
        mutableStateOf("")
    }
    val (tfRut, fRut) = rememberSaveable {
        mutableStateOf("")
    }
    val (tfNacimiento, fNacimiento) = rememberSaveable {
        mutableStateOf("")
    }
    val (tfEmail, fEmail) = rememberSaveable {
        mutableStateOf("")
    }
    val (tfTelefono, fTelefono) = rememberSaveable {
        mutableStateOf("")
    }

    Box(
        contentAlignment = Alignment.Center, modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Solicitud Cuenta", fontWeight = FontWeight.Bold, fontSize = 30.sp
            )
            TextField(value = tfNombre, onValueChange = { fNombre(it) }, placeholder = {
                Text(phNombre)
            })
            TextField(value = tfRut, onValueChange = { fRut(it) }, placeholder = {
                Text(phRut)
            })
            TextField(value = tfNacimiento, onValueChange = { fNacimiento(it) }, placeholder = {
                Text(phNacimiento)
            })
            TextField(value = tfEmail, onValueChange = { fEmail(it) }, placeholder = {
                Text(phEmail)
            })
            TextField(value = tfTelefono, onValueChange = { fTelefono(it) }, placeholder = {
                Text(phTelefono)
            })
            Spacer(modifier = Modifier.height(15.dp))
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = btnColor)
            ) {
                Icon(
                    painter = painterResource(R.drawable.camera),
                    contentDescription = "Camara", modifier = Modifier
                        .size(20.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(text = "Cedula frontal")
            }
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = btnColor)
            ) {
                Icon(
                    painter = painterResource(R.drawable.camera),
                    contentDescription = "Camara", modifier = Modifier
                        .size(20.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(text = "Cedula trasera")
            }
            Spacer(modifier = Modifier.height(35.dp))
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = btnColor)
            ) {
                Text(text = "Solicitar")


            }


        }
    }
}