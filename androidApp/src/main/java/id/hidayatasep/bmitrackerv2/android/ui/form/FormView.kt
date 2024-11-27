package id.hidayatasep.bmitrackerv2.android.ui.form

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel

@Composable
fun FormView(viewModel: FormViewModel = koinViewModel()) {
    val formState by viewModel.formState.collectAsState()
    FormView(formState, viewModel)
}

@Composable
private fun FormView(
    formState: FormState,
    listener: FormListener? = null
) {

    var showSnackbar by remember { mutableStateOf(false) }
    LaunchedEffect(formState.errorMessage) {
        if (formState.errorMessage != null) {
            showSnackbar = true
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = SnackbarHostState()) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
                .padding(16.dp)
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            OutlinedTextField(
                value = formState.age,
                onValueChange = { listener?.onAgeChange(it) },
                label = { Text("Age") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = formState.weight,
                onValueChange = { listener?.onWeightChange(it) },
                label = { Text("Weight (kg)") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = formState.height,
                onValueChange = { listener?.onHeightChange(it) },
                label = { Text("Height (cm)") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                listener?.calculateBMI()
            }) {
                Text("Submit")
            }

            if (showSnackbar) {
                LaunchedEffect(showSnackbar) {
                    SnackbarHostState().showSnackbar(formState.errorMessage ?: "")
                    showSnackbar = false
                }
            }
            formState.bmiResult?.let {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = it.toString(),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            formState.message?.let {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun UserInputFormPreview() {
    FormView(formState = FormState("20", "55.5", "165"))
}