package com.example.androiddrugbugtests.repository

import android.util.Log
import com.example.androiddrugbugtests.models.InteractionsResponseModel
import com.example.androiddrugbugtests.network.MedSearchTestsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
class InteractionsRespository {

    private val TAG = "InteractionsRepository"

//
//    private var _interactionsResponse: InteractionsResponseModel? = null
//    val interactionsResponse = _interactionsResponse

    suspend fun callMedSearchTests(interactor: String): InteractionsResponseModel? {
        withContext(Dispatchers.IO) {
            var _interactionsResponse: InteractionsResponseModel? = null
            try {
                _interactionsResponse =
                    MedSearchTestsApi.retrofitService.getInteractionsResponse(
                        interactor = interactor
                    )
                Log.d(TAG, _interactionsResponse.interactionsResponse[0].medicine)
            } catch (e: Exception) {
                Log.d(TAG, "Error getting interactions response")
                e.printStackTrace()
            }
            return@withContext _interactionsResponse
        }

        return null
    }

}