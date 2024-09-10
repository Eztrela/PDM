package com.example.navegacao1.ui.telas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.navegacao1.model.dados.Endereco
import com.example.navegacao1.model.dados.RetrofitClient
import com.example.navegacao1.model.dados.Usuario
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun TelaPrincipal(modifier: Modifier = Modifier, onLogoffClick: () -> Unit) {
    var scope = rememberCoroutineScope()
    var usuarios by remember { mutableStateOf<List<Usuario>>(emptyList()) }
    var endereco by remember { mutableStateOf<Endereco>(Endereco()) }
    var usuarioIdToFind by remember { mutableStateOf("") }
    var usuarioEncontrado by remember { mutableStateOf<Usuario?>(null) }

    // Campos para adicionar um novo usuário
    var novoUsuarioNome by remember { mutableStateOf("") }
    var novoUsuarioSenha by remember { mutableStateOf("") }

    Column(modifier = modifier.padding(16.dp)) {
        Text(
            text = "Tela Principal",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Campo para buscar um usuário por ID
        TextField(
            value = usuarioIdToFind,
            onValueChange = { usuarioIdToFind = it },
            label = { Text("Buscar por ID") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Button(
            onClick = {
                scope.launch {
                    usuarioEncontrado = buscarUsuarioPorId(usuarioIdToFind)
                }
            },
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text("Buscar Usuário por ID")
        }

        if (usuarioEncontrado != null) {
            Text("Usuário Encontrado:", style = MaterialTheme.typography.titleMedium)
            Text("Nome: ${usuarioEncontrado!!.nome}", style = MaterialTheme.typography.bodyLarge)
            Text("ID: ${usuarioEncontrado!!.id}", style = MaterialTheme.typography.bodyMedium)
            Text("Senha: ${usuarioEncontrado!!.senha}", style = MaterialTheme.typography.bodyMedium) // Atenção: Exibir senha pode não ser seguro
            Divider(modifier = Modifier.padding(vertical = 8.dp))
        }

        // Campos para adicionar um novo usuário
        Text(
            text = "Adicionar Novo Usuário",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(vertical = 16.dp)
        )
        TextField(
            value = novoUsuarioNome,
            onValueChange = { novoUsuarioNome = it },
            label = { Text("Nome do Novo Usuário") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        TextField(
            value = novoUsuarioSenha,
            onValueChange = { novoUsuarioSenha = it },
            label = { Text("Senha do Novo Usuário") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Button(
            onClick = {
                scope.launch {
                    val novoId = gerarIdUnico()
                    val novoUsuario = Usuario(id = novoId, nome = novoUsuarioNome, senha = novoUsuarioSenha)
                    val usuarioAdicionado = addUsuario(novoUsuario)
                    usuarios = usuarios + usuarioAdicionado // Atualiza a lista com o novo usuário
                }
            },
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text("Adicionar Usuário")
        }

        Button(
            onClick = {
                scope.launch {
                    usuarios = getUsuarios()
                    endereco = getEndereco() // Atualiza o endereço
                }
            },
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text("Carregar")
        }

        Button(
            onClick = { onLogoffClick() },
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text("Sair")
        }

        // Exibe o endereço
        Text(
            text = "Endereço: ${endereco.logradouro}",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        // Carrega sob demanda à medida que o usuário rola na tela
        LazyColumn {
            items(usuarios) { usuario ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = "Nome: ${usuario.nome}", style = MaterialTheme.typography.bodyLarge)
                        Text(text = "ID: ${usuario.id}", style = MaterialTheme.typography.bodyMedium)
                        // Exibindo outras informações do usuário se necessário
                        Text(text = "Senha: ${usuario.senha}", style = MaterialTheme.typography.bodyMedium) // Atenção: Exibir senha pode não ser seguro

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(
                            onClick = {
                                scope.launch {
                                    excluirUsuario(usuario.id)
                                    usuarios = usuarios.filter { it.id != usuario.id } // Atualiza a lista após exclusão
                                }
                            },
                            modifier = Modifier.align(Alignment.End)
                        ) {
                            Text("Excluir Usuário")
                        }
                    }
                }
            }
        }
    }
}

suspend fun buscarUsuarioPorId(id: String): Usuario? {
    return withContext(Dispatchers.IO) {
        RetrofitClient.usuarioService.buscarPorId(id)
    }
}

suspend fun addUsuario(usuario: Usuario): Usuario {
    return withContext(Dispatchers.IO) {
        RetrofitClient.usuarioService.adicionar(usuario)
    }
}

suspend fun getUsuarios(): List<Usuario> {
    return withContext(Dispatchers.IO) {
        RetrofitClient.usuarioService.listar()
    }
}

suspend fun getEndereco(): Endereco {
    return withContext(Dispatchers.IO) {
        RetrofitClient.usuarioService.getEndereco()
    }
}

suspend fun gerarIdUnico(): String {
    val usuarios = getUsuarios()
    val ids = usuarios.map { it.id.toIntOrNull() ?: 0 }
    val novoId = (ids.maxOrNull() ?: 0) + 1
    return novoId.toString()
}

suspend fun excluirUsuario(id: String) {
    withContext(Dispatchers.IO) {
        RetrofitClient.usuarioService.excluir(id)
    }
}
