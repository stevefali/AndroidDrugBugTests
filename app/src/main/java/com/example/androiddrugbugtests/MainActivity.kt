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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddrugbugtests.ui.InteractionCard
import com.example.androiddrugbugtests.ui.theme.AndroidDrugBugTestsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
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

                        Text(text = "Interactions", fontSize = 24.sp)
                        SearchBar(
                            query = interactor,
                            onQueryChange = { interactor = it },
                            onSearch = { searchInteractions(interactor) },
                            active = false,
                            onActiveChange = {},
                            placeholder = { Text(text = "Enter a food, drink or drug") },
                            leadingIcon = {
                                IconButton(onClick = { searchInteractions(interactor) }) {
                                    Icon(
                                        Icons.Default.Search,
                                        contentDescription = "Search",
                                        modifier = Modifier,
                                        Color(
                                            0xff56cc9d
                                        )
                                    )
                                }
                            }
                        ) {}

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
                        interactions?.let {
                            Text(
                                text = it.disclaimer,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                        }
                    }

                }
            }
        }
    }

    private fun searchInteractions(interactor: String) {
        if (interactor.isNotBlank()) {
            viewModel.getInteractions(interactor)
        }
    }
}