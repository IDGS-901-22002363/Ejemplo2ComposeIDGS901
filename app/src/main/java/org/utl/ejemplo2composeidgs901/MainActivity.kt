package org.utl.ejemplo2composeidgs901 // <--- CHANGE THIS LINE

import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import org.utl.ejemplo2composeidgs901.ui.theme.Ejemplo2ComposeIDGS901Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            Ejemplo2ComposeIDGS901Theme {

                /*
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "inicio") {
                    composable("inicio") {
                        PantallaInicio(navController)
                    }
                    composable("detalle/{nombre}") { backStackEntry ->
                        val nombre = backStackEntry.arguments?.getString("nombre") ?: "Invitado"
                        PantallaDetalle(navController, nombre)
                    }
                }
                */
                operacionesDosNumeros()
            }
        }
    }
}

@Composable
fun PantallaInicio(navController: NavHostController) {
    var nombre by remember { mutableStateOf("") }
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Pantalla de inicio")
        Spacer(modifier = Modifier.size(16.dp))
        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier= Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(16.dp))
        Button(onClick = {
            val nombreToSend = nombre.ifBlank { "Invitado" }
            navController.navigate("detalle/$nombreToSend")
        })  {
            Text("Ingresar")
        }
    }
}

@Composable
fun PantallaDetalle(navController: NavHostController, nombre: String){
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Hola $nombre")
        Button(onClick = {
            navController.navigate("inicio") {
                popUpTo("inicio") { inclusive = true }
            }
        }){
            Text("Volver")
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Ejemplo2ComposeIDGS901Theme {
        Greeting("Android")
    }
}