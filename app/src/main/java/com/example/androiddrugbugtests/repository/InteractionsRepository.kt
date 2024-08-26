package com.example.androiddrugbugtests.repository

import android.util.Log
import com.example.androiddrugbugtests.models.InteractionsResponseModel
import com.example.androiddrugbugtests.network.MedSearchTestsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Singleton

@Singleton
class InteractionsRepository {

    private val TAG = "InteractionsRepository"

    suspend fun callMedSearchTests(interactor: String): InteractionsResponseModel? {
        var interactionsResponse: InteractionsResponseModel? = null
        withContext(Dispatchers.IO) {
            try {
                interactionsResponse =
                    MedSearchTestsApi.retrofitService.getInteractionsResponse(
                        interactor = interactor
                    )
            } catch (e: Exception) {
                Log.d(TAG, "Error getting interactions response")
                e.printStackTrace()
            }
        }
        return interactionsResponse
    }

}