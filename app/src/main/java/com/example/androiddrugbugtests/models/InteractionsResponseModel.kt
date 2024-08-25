package com.example.androiddrugbugtests.models

data class InteractionsResponseModel(
    val interactionsResponse: List<MedItem>
)

data class MedItem(
    val medicine: String,
    val matches: List<String>
)