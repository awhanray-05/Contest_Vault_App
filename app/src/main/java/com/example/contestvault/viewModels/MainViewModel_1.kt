package com.example.contestvault.viewModels

/*
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contestvault.data.Result
import com.example.contestvault.data.resultService
import kotlinx.coroutines.launch

class MainViewModel_1: ViewModel() {

    private val _resultsState = mutableStateOf(ResultState())
    val resultsState: State<ResultState> = _resultsState

    init {
        fetchResults("START167", "C")
    }

    private fun fetchResults(contestName: String, contestCategory: String) {
        // Launch a coroutine to fetch results from the API
        viewModelScope.launch {
            try {
                val response = resultService.getResults(contestName, contestCategory)
                _resultsState.value = _resultsState.value.copy(
                    loading = false,
                    list = response.resultList,
                    error = null
                )
            } catch (e: Exception) {
                _resultsState.value = _resultsState.value.copy(
                    loading = false,
                    error = e.message
                )
            }
        }
    }

    data class ResultState (
        val loading: Boolean = true,
        val list: List<Result> = emptyList(),
        val error: String? = null
    )
}
*/


//import kotlinx.coroutines.runBlocking
//
//fun main() {
//    runBlocking {
//        try {
//            val response = resultService.getResults("START169", "D")
//            println("API Response: $response")
//        } catch (e: Exception) {
//            println("Error: ${e.message}")
//        }
//    }
//}



//import androidx.compose.runtime.State
//import androidx.compose.runtime.mutableStateOf
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import kotlinx.coroutines.launch
//
//class MainViewModel_1 : ViewModel() {
//
//    private val _resultsState = mutableStateOf(ResultState())
//    val resultsState: State<ResultState> = _resultsState
//
//    init {
//        fetchResults("START167", "C")
//    }
//
//    private fun fetchResults(contestName: String, contestCategory: String) {
//        viewModelScope.launch {
//            try {
//                val response = resultService.getResults(contestName, contestCategory)
//                _resultsState.value = _resultsState.value.copy(
//                    loading = false,
//                    list = response.resultList ?: emptyList(), // Provide default empty list
//                    error = null
//                )
//            } catch (e: Exception) {
//                _resultsState.value = _resultsState.value.copy(
//                    loading = false,
//                    error = e.message
//                )
//            }
//        }
//    }
//
//    data class ResultState(
//        val loading: Boolean = true,
//        val list: List<Result> = emptyList(),
//        val error: String? = null
//    )
//}
