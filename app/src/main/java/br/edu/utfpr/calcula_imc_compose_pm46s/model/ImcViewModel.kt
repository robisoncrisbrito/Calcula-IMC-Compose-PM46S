package br.edu.utfpr.calcula_imc_compose_pm46s.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ImcViewModel: ViewModel() {
    var peso by mutableStateOf("")
       private set
    var altura by mutableStateOf("")
        private set
    var resultado by mutableStateOf("0.0")
        private set

    fun onPesoChange(value: String) {
        peso = value
    }

    fun onAlturaChange(value: String) {
        altura = value
    }

    fun calcularIMC() {
        val pesoValor = peso.toDoubleOrNull()
        val alturaValor = altura.toDoubleOrNull()

        if (pesoValor != null && alturaValor != null) {
            val imc = pesoValor / (alturaValor * alturaValor)
            resultado = String.format( "%.2f", imc)
        }
    }

    fun limparTela() {
        peso = ""
        altura = ""
        resultado = "0.00"
    }

}