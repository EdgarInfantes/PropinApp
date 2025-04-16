package dev.einfantesv

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp


@Composable
fun TipCalculatorScreen(){
    var montoText by remember { mutableStateOf("") }
    var porcentajeText by remember {mutableStateOf("0%") }
    var porcentajes = listOf(0, 5, 10, 15, 20, 25, 30)
    var porcentajeIndex by remember { mutableStateOf(0) }
    var propina by remember { mutableStateOf(0.0) }
    var total by remember { mutableStateOf(0.0) }
    var resultadoText by remember { mutableStateOf("") }

    Column(
        modifier  = Modifier.fillMaxSize().padding(26.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        //Para el título
        Text(
            text = "Calculadora de Propinas",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.titleMedium
        )

        //Para ingresar la propina
        TextField(
            value = montoText,
            onValueChange = { montoText = it },
            label = { Text("Monto en soles") },
            modifier = Modifier.align(Alignment.CenterHorizontally),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        //Para el SlideBar con los porcentajes

        Text(
            text = "Porcentaje de propina: $porcentajeText",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Slider(
            value = porcentajeIndex.toFloat(),
            onValueChange = {
                porcentajeIndex = it.toInt()
                porcentajeText = "${porcentajes[porcentajeIndex]}%"
            },
            valueRange = 0f..(porcentajes.lastIndex).toFloat(),
            steps = porcentajes.size - 2,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        //Para el botón calcular
        Button(
            onClick = {
                val monto = montoText.toDoubleOrNull() ?: 0.0
                val porcentaje = porcentajes[porcentajeIndex]
                propina = monto * (porcentaje.toDouble() / 100)
                total = monto + propina
                resultadoText = "Total a pagar: S/. %.2f".format(total)
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ){
            Text("Calcular")
        }
        
        //Para el resultado: Si tiene texto se muestra
        if (resultadoText.isNotEmpty()) {
            Text(
                text = resultadoText,
            )
        }
    }
}