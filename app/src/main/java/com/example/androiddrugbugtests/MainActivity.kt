package com.example.androiddrugbugtests

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
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
                    val interactions by viewModel.interactionsResponse.collectAsState()
                    Column {
                        Greeting(
                            name = "Android",
                            modifier = Modifier
                                .padding(innerPadding)
                        )
                        Button(onClick = { viewModel.getInteractions("grapefruit") }) {
                            Text(text = "Get Interactions")
                        }
                        if (viewModel.isResultLoading.value) {
                            Text(text = "Loading...")
                        }
                        interactions?.interactionsResponse?.get(0)?.let {
                            Text(text = it.medicine)
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidDrugBugTestsTheme {
        Greeting("Android")
    }

}