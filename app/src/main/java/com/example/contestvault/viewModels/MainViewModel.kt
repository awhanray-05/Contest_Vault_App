package com.example.contestvault.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contestvault.data.codechef.CodeChefUserData
import com.example.contestvault.data.codechef.codechefService
import com.example.contestvault.screens.Screen
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private var _currentDrawerScreen by mutableStateOf<Screen>(Screen.DrawerScreen.Profile)
    val currentDrawerScreen: Screen get() = _currentDrawerScreen

    private var _currentBottomBarScreen by mutableStateOf<Screen>(Screen.BottomScreen.CodeChef)
    val currentBottomBarScreen: Screen get() = _currentBottomBarScreen

    fun setCurrentDrawerScreen(screen: Screen.DrawerScreen) {
        _currentDrawerScreen = screen
    }

    fun setCurrentBottomBarScreen(screen: Screen.BottomScreen) {
        _currentBottomBarScreen = screen
    }

//    private val _userHandle by mutableStateOf("")
//    var userHandle: String get() = _userHandle

    private val _cCuserDataState = mutableStateOf(CodeChefDataState())
    val cCuserDataState: CodeChefDataState get() = _cCuserDataState.value

    init {
        fetchCCUserProfileData("byteninja_05")
    }
    fun fetchCCUserProfileData(userHandle: String) {
        viewModelScope.launch {
            try {
                val response = codechefService.getUserProfile(userHandle)
                if(response.success == true) {
                    _cCuserDataState.value = _cCuserDataState.value.copy(
                        loading = false,
                        data = response,
                        error = null
                    )
                } else {
                    _cCuserDataState.value = _cCuserDataState.value.copy(
                        loading = false,
                        error = "Data Fetched = ${response.status}, Error Status : ${response.status}"
                    )
                }
            } catch(e: Exception) {
                _cCuserDataState.value = _cCuserDataState.value.copy(
                    loading = false,
                    error = "Error fetching User Data ${e.message}"
                )
            }
        }
    }

    data class CodeChefDataState (
        val loading: Boolean = true,
        val data: CodeChefUserData = CodeChefUserData(true, 200, "https://cdn.codechef.com/sites/default/files/uploads/pictures/89419e9e5c1ee0b459adb51c98d1d823.jpeg", "Awhan Ray", 1496, 1496, "https://cdn.codechef.com/download/flags/24/in.png", "India", 31805, 28705, "2★"),
        val error: String? = null
    )
}