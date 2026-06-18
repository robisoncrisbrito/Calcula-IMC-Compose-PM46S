package br.edu.utfpr.calcula_imc_compose_pm46s

import android.os.Bundle
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.edu.utfpr.calcula_imc_compose_pm46s.model.ImcViewModel
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
fun CalculaIMCScreen(
    modifier: Modifier = Modifier,
    viewModel: ImcViewModel = viewModel()
) {

    var peso = viewModel.peso
    var altura = viewModel.altura
    var resultado = viewModel.resultado

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedTextField(
            value = viewModel.peso,
            onValueChange = {viewModel.onPesoChange( it )},
            label = {Text( "Peso em Kg")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            keyboardOptions = KeyboardOptions( keyboardType = KeyboardType.Number)
        )

        OutlinedTextField(
            value = viewModel.altura,
            onValueChange = {viewModel.onAlturaChange(it)},
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
            onCalcularClick = { viewModel.calcularIMC()},
            onLimparClick = { viewModel.limparTela()}
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
                .weight(1f),
            colors = ButtonDefaults.buttonColors( containerColor = MaterialTheme.colorScheme.secondary)
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