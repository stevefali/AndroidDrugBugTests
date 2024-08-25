package com.example.androiddrugbugtests

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddrugbugtests.models.InteractionsResponseModel
import com.example.androiddrugbugtests.repository.InteractionsRespository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    interactionsRepository: InteractionsRespository
) : ViewModel() {

    private val _stevesMessage = MutableStateFlow("Yo")
    val stevesMessage = _stevesMessage.asStateFlow()

    fun changeMessage() {
        _stevesMessage.value += "r"
    }

    private val interactionsRepository = interactionsRepository


    private val _interactionsResponse = MutableStateFlow<InteractionsResponseModel?>(null)
    val interactionsResponse = _interactionsResponse.asStateFlow()


    fun getInteractions(interactor: String) {
        viewModelScope.launch {
            _interactionsResponse.value = interactionsRepository.callMedSearchTests(interactor)
        }
    }


}