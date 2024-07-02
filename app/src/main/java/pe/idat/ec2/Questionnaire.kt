package pe.idat.ec2.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun Questionnaire(modifier: Modifier = Modifier) {
    var selectedSkills by remember { mutableStateOf(listOf<String>()) }
    var jobSignificance by remember { mutableStateOf("Mucho") }
    var jobPayment by remember { mutableStateOf("Bien") }
    var worksUnderPressure by remember { mutableStateOf(true) }
    var growthOpportunity by remember { mutableStateOf(true) }

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
        SwitchWithLabel(isChecked = growthOpportunity, onCheckedChange = { growthOpportunity = it })

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
            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
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
            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
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
    Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
        Switch(
            checked = isChecked,
            onCheckedChange = onCheckedChange
        )
        Text(text = if (isChecked) "SI" else "NO")
    }
}
