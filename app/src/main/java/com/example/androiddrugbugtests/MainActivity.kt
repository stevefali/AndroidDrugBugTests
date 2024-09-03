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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

                        SearchBar(
                            query = interactor,
                            onQueryChange = { interactor = it },
                            onSearch = { searchinteractions(interactor) },
                            active = false,
                            onActiveChange = {},
                            placeholder = { Text(text = "Enter a food, drink or drug") },
                            leadingIcon = {
                                IconButton(onClick = { searchinteractions(interactor) }) {
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

    fun searchinteractions(interactor: String) {
        if (interactor.isNotBlank()) {
            viewModel.getInteractions(interactor)
        }
    }
}