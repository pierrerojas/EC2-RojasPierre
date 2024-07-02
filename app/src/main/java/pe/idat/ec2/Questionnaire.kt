package pe.idat.ec2.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp

// Define la clase Evento con los atributos necesarios
data class Evento(val titulo: String, val descripcion: String, val fecha: String)

@Composable
fun Questionnaire(modifier: Modifier = Modifier) {
    var selectedSkills by remember { mutableStateOf(listOf<String>()) }
    var jobSignificance by remember { mutableStateOf("Mucho") }
    var jobPayment by remember { mutableStateOf("Bien") }
    var worksUnderPressure by remember { mutableStateOf(true) }
    var growthOpportunity by remember { mutableStateOf(false) }

    // Lista de eventos internacionales
    val eventos = remember {
        listOf(
            Evento("Evento 1", "Descripción del Evento 1", "01/01/2024"),
            Evento("Evento 2", "Descripción del Evento 2", "02/02/2024"),
            Evento("Evento 3", "Descripción del Evento 3", "03/03/2024"),
            Evento("Evento 4", "Descripción del Evento 4", "04/04/2024"),
            Evento("Evento 5", "Descripción del Evento 5", "05/05/2024"),
            Evento("Evento 6", "Descripción del Evento 6", "06/06/2024"),
            Evento("Evento 7", "Descripción del Evento 7", "07/07/2024"),
            Evento("Evento 8", "Descripción del Evento 8", "08/08/2024"),
            Evento("Evento 9", "Descripción del Evento 9", "09/09/2024"),
            Evento("Evento 10", "Descripción del Evento 10", "10/10/2024"),
            Evento("Evento 11", "Descripción del Evento 11", "11/11/2024"),
            Evento("Evento 12", "Descripción del Evento 12", "12/12/2024")
        )
    }

    Column(modifier = modifier) {
        Text(text = "1. Marque sus habilidades.")
        SkillCheckboxes(selectedSkills) { selectedSkills = it }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "2. ¿Cuán significativo es tu trabajo?")
        RadioButtonGroup(options = listOf("Mucho", "Más o menos", "Poco"), selectedOption = jobSignificance) {
            jobSignificance = it
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "3. ¿Qué tan bien te pagan en el trabajo que haces?")
        RadioButtonGroup(options = listOf("Bien", "Regular", "Mal"), selectedOption = jobPayment) {
            jobPayment = it
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "4. ¿Trabajas bajo presión?")
        SwitchWithLabel(isChecked = worksUnderPressure, onCheckedChange = { worksUnderPressure = it })

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "5. ¿Tienes oportunidad de crecimiento en tu trabajo?")
        SwitchWithLabel(isChecked = growthOpportunity, onCheckedChange = {
            growthOpportunity = it
            if (it) {
                // Si se marca la opción, mostrar los eventos internacionales
                // aquí puedes cambiar el estado o realizar alguna acción adicional
            }
        })

        Spacer(modifier = Modifier.height(16.dp))

        // Lista de eventos internacionales renderizada condicionalmente
        if (growthOpportunity) {
            EventosList(eventos)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { /* TODO: Add resolve logic */ }) {
            Text(text = "Resolver")
        }
    }
}

@Composable
fun SkillCheckboxes(selectedSkills: List<String>, onSkillsChanged: (List<String>) -> Unit) {
    val skills = listOf("Autoconocimiento", "Empatía", "Comunicación asertiva", "Toma de decisiones", "Pensamiento crítico", "Ninguno")
    Column {
        skills.forEach { skill ->
            val isSelected = selectedSkills.contains(skill)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = isSelected,
                    onCheckedChange = {
                        if (isSelected) {
                            onSkillsChanged(selectedSkills - skill)
                        } else {
                            onSkillsChanged(selectedSkills + skill)
                        }
                    }
                )
                Text(text = skill)
            }
        }
    }
}

@Composable
fun RadioButtonGroup(options: List<String>, selectedOption: String, onOptionSelected: (String) -> Unit) {
    Column {
        options.forEach { option ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = option == selectedOption,
                    onClick = { onOptionSelected(option) }
                )
                Text(text = option)
            }
        }
    }
}

@Composable
fun SwitchWithLabel(isChecked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Switch(
            checked = isChecked,
            onCheckedChange = onCheckedChange
        )
        Text(text = if (isChecked) "SI" else "NO")
    }
}

@Composable
fun EventosList(eventos: List<Evento>) {
    LazyColumn {
        items(eventos) { evento ->
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                backgroundColor = Color.LightGray,
                elevation = 4.dp
            ) {
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(
                        text = evento.titulo,
                        style = MaterialTheme.typography.subtitle1,
                        fontStyle = FontStyle.Italic
                    )
                    Text(
                        text = evento.descripcion,
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                    Text(
                        text = "Fecha: ${evento.fecha}",
                        style = MaterialTheme.typography.body2
                    )
                }
            }
        }
    }
}
