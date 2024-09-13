package com.example.android_gerenciaestado

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.android_gerenciaestado.ui.theme.Android_GerenciaEstadoTheme
import com.example.android_gerenciaestado.ui.viewmodels.ContadorViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Android_GerenciaEstadoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column {
                        ContadorStateful(
                            modifier = Modifier.padding(innerPadding)
                        )






//                        var contador by remember { mutableIntStateOf(0) }
                        val viewModel by viewModels<ContadorViewModel>()
                        val contador by viewModel.contador.collectAsState()
                        ContadorStateless(contador, onActionContador = {viewModel.incrementar()}, modifier = Modifier.padding(innerPadding), text="Incrementar")
                        ContadorStateless(contador, onActionContador = {viewModel.incrementar()}, modifier = Modifier.padding(innerPadding),text="Incrementar")
                        ContadorStateless(contador, onActionContador = { viewModel.decrementar()}, modifier = Modifier.padding(innerPadding), text="Decrementar")
                    }
                }
            }
        }
    }
}

@Composable
fun ContadorStateless(contador: Int, onActionContador: () -> Unit, modifier: Modifier = Modifier, text:String) {

    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Contador: $contador"
        )
        Button(onClick = onActionContador) {
            Text("$text aqui")

        }

    }

}


@Composable
fun ContadorStateful(modifier: Modifier = Modifier) {
    var contador by remember { mutableIntStateOf(0) }

    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Contador: $contador"
        )
        Button(onClick = {contador++}) {
            Text("Incremente aqui")

        }

    }

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Android_GerenciaEstadoTheme {
    }
}