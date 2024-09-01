package com.example.androiddrugbugtests

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddrugbugtests.ui.InteractionCard
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
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 8.dp)
                ) { innerPadding ->
                    val interactions by viewModel.interactionsResponse.collectAsState()
                    Column(modifier = Modifier.padding(innerPadding)) {
                        Button(onClick = { viewModel.getInteractions("grapefruit") }) {
                            Text(text = "Get Interactions")
                        }
                        if (viewModel.isResultLoading.value) {
                            Text(text = "Loading...")
                        }
                        LazyColumn {
                            interactions?.let {
                                items(it.interactionsResponse) { interaction ->
                                    InteractionCard(interaction)
                                }
                            }
                        }
                    }

                }
            }
        }
    }
}