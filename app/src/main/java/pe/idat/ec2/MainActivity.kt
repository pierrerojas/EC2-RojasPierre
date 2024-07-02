package pe.idat.ec2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pe.idat.ec2.ui.theme.EC2Theme
import pe.idat.ec2.ui.components.Questionnaire

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EC2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold(
                        topBar = {
                            MyAppTopAppBar(title = "Cuestionario")
                        }
                    ) { paddingValues ->
                        Questionnaire(modifier = Modifier.padding(paddingValues).padding(16.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun MyAppTopAppBar(title: String) {
    TopAppBar(
        title = { Text(text = title) }
    )
}
