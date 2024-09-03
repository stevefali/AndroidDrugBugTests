package com.example.androiddrugbugtests.models

data class InteractionsResponseModel(
    val interactionsResponse: List<MedItem>,
    val disclaimer: String
)

data class MedItem(
    val medicine: String,
    val matches: List<String>
)