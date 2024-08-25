package com.example.androiddrugbugtests

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddrugbugtests.ui.theme.AndroidDrugBugTestsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidDrugBugTestsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val stevesMessage by viewModel.stevesMessage.collectAsState()
                    val interactions by viewModel.interactionsResponse.collectAsState()
                    Column {
                        Greeting(
                            name = "Android",
                            modifier = Modifier
                                .padding(innerPadding)
                        )
                        SteveMessage(
                            text = stevesMessage,
                            modifier = Modifier
                                .padding(vertical = 16.dp)
                                .clickable(onClick = {
                                    viewModel.changeMessage()
                                    Log.d("MainActivity", "Message Clicked!")
                                })

                        )
                        Button(onClick = { viewModel.getInteractions("grapefruit") }) {
                            Text(text = "Get Interactions")
                        }
                        interactions?.interactionsResponse?.get(0)?.let { Text(text = it.medicine)
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun SteveMessage(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidDrugBugTestsTheme {
        Greeting("Android")
    }

}