package com.example.androiddrugbugtests.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.androiddrugbugtests.models.MedItem

@Composable
fun InteractionCard(interaction: MedItem) {

    Card(modifier = Modifier.padding(top = 8.dp)) {
        Text(text = interaction.medicine)
        HorizontalDivider()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize()
        ) {
            interaction.matches.forEach { interaction ->
                Text(text = interaction)
            }
        }
    }
}
