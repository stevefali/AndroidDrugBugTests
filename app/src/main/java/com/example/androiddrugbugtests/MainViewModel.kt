package com.example.androiddrugbugtests

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddrugbugtests.models.InteractionsResponseModel
import com.example.androiddrugbugtests.repository.InteractionsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val interactionsRepository: InteractionsRepository
) : ViewModel() {

    private val _interactionsResponse = MutableStateFlow<InteractionsResponseModel?>(null)
    val interactionsResponse = _interactionsResponse.asStateFlow()

    val isResultLoading = mutableStateOf(false);

    fun getInteractions(interactor: String) {
        viewModelScope.launch {
            isResultLoading.value = true
            _interactionsResponse.value = interactionsRepository.callMedSearchTests(interactor)
            isResultLoading.value = false
        }
    }


}