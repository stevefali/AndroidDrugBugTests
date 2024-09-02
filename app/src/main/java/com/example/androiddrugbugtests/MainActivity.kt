package com.example.androiddrugbugtests

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
                    var interactor by remember {
                        mutableStateOf("")
                    }
                    Column(modifier = Modifier.padding(innerPadding)) {
                        Row {
                            TextField(value = interactor, onValueChange = { interactor = it })
                            Button(
                                onClick = { viewModel.getInteractions(interactor) },
                                enabled = interactor.isNotBlank()
                            ) {
                                Text(text = "Get Interactions")
                            }
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