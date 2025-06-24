package org.utl.ejemplo2composeidgs901

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.fillMaxWidth

@Composable
fun operacionesDosNumeros() {
    var num1 by remember { mutableStateOf("") }
    var num2 by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }
    var operacionSeleccionada by remember { mutableStateOf("suma") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        TextField(
            value = num1,
            onValueChange = { newValue -> num1 = newValue.filter { it.isDigit() || it == '.' } },
            label = { Text("Número 1") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        TextField(
            value = num2,
            onValueChange = { newValue -> num2 = newValue.filter { it.isDigit() || it == '.' } },
            label = { Text("Número 2") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        // RadioButtons para seleccionar la operación
        Column {
            listOf("suma", "resta", "multiplicacion", "division").forEach { operacion ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = (operacion == operacionSeleccionada),
                            onClick = { operacionSeleccionada = operacion },
                            role = Role.RadioButton
                        )
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (operacion == operacionSeleccionada),
                        onClick = { operacionSeleccionada = operacion }
                    )
                    Text(text = operacion, modifier = Modifier.padding(start = 8.dp))
                }
            }
        }

        Button(onClick = {
            val n1 = num1.toDoubleOrNull()
            val n2 = num2.toDoubleOrNull()
            resultado = if (n1 != null && n2 != null) {
                when (operacionSeleccionada) {
                    "suma" -> "Resultado: ${n1 + n2}"
                    "resta" -> "Resultado: ${n1 - n2}"
                    "multiplicacion" -> "Resultado: ${n1 * n2}"
                    "division" -> {
                        if (n2 != 0.0) {
                            "Resultado: ${n1 / n2}"
                        } else {
                            "Error: No se puede dividir por cero."
                        }
                    }
                    else -> "Error: Operación no válida."
                }
            } else {
                "Error: Ingrese números válidos."
            }
        }) {
            Text("Calcular")
        }
        Text(text = resultado)
    }
}