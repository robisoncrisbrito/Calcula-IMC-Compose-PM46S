package br.edu.utfpr.calcula_imc_compose_pm46s

import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.edu.utfpr.calcula_imc_compose_pm46s.ui.theme.CalculaIMCComposePM46STheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculaIMCComposePM46STheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CalculaIMCScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun CalculaIMCScreen(modifier: Modifier = Modifier) {

    var peso by rememberSaveable { mutableStateOf("") }
    var altura by rememberSaveable { mutableStateOf("") }
    var resultado by rememberSaveable { mutableStateOf("0.0") }

    val calcularIMC = {
        val pesoValor = peso.toDoubleOrNull()
        val alturaValor = altura.toDoubleOrNull()

        if (pesoValor != null && alturaValor != null) {
            val imc = pesoValor / (alturaValor * alturaValor)
            resultado = String.format( "%.2f", imc)
        }
    }

    val limparTela = {
        peso = ""
        altura = ""
        resultado = "0.00"
    }


    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedTextField(
            value = peso,
            onValueChange = {peso = it},
            label = {Text( "Peso em Kg")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            keyboardOptions = KeyboardOptions( keyboardType = KeyboardType.Number)
        )

        OutlinedTextField(
            value = altura,
            onValueChange = {altura = it},
            label = {Text( "Altura em m")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            keyboardOptions = KeyboardOptions( keyboardType = KeyboardType.Number)
        )

        if ( resultado.toDouble() > 0 ) {
            PanelResult(resultado)
        }

        PanelButton(
            onCalcularClick = calcularIMC,
            onLimparClick = limparTela
        )


    } //fim do column

}//fim do CalculaIMCScreen

@Composable
fun PanelResult( resultado: String) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Resultado:",
            modifier = Modifier.padding( 16.dp)
        )

        Text(
            text = resultado,
            modifier = Modifier.padding( 16.dp),
            style = MaterialTheme.typography.headlineLarge
        )

    }

}

@Composable
fun PanelButton(
    onCalcularClick: () -> Unit,
    onLimparClick: () -> Unit
) {
    Row{
        Button(
            onClick = onCalcularClick,
            modifier = Modifier
                .padding(16.dp)
                .weight(1f)
        ) {
            Text(text = "Calcular")
        }

        Button(
            onClick = onLimparClick,
            modifier = Modifier
                .padding(16.dp)
                .weight(1f)
        ) {
            Text(text = "Limpar")
        }
    }

}



@Preview(showBackground = true)
@Composable
fun CalculaIMCScreenPreview() {
    CalculaIMCComposePM46STheme {
        CalculaIMCScreen()
    }
}