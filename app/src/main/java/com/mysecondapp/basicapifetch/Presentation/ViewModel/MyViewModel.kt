package com.mysecondapp.basicapifetch.Presentation.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mysecondapp.basicapifetch.Data.models.ApiRes_Coffee_hotItem // Import the item model
import com.mysecondapp.basicapifetch.Data.repo.Repo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class AppState(
    val is_loading : Boolean? = false,
    val coffeeList : List<ApiRes_Coffee_hotItem>? = null,
    val is_error : String? = null
)

class MyViewModel : ViewModel() {
    private val stateOfFlow = MutableStateFlow(AppState())
    val currentState = stateOfFlow.asStateFlow()
    private val repo : Repo = Repo()

    fun loadCoffeeData(){
        if (stateOfFlow.value.coffeeList != null && stateOfFlow.value.is_error == null) {
            if (stateOfFlow.value.is_loading == true) {
                 stateOfFlow.value = stateOfFlow.value.copy(is_loading = false)
            }
            return
        }
        viewModelScope.launch {
            stateOfFlow.value = AppState(is_loading = true, is_error = null)
            repo.getdata().collect{ apiResult ->
                if (apiResult.loading == true && apiResult.coffeeList == null && apiResult.error == null) {

                    if (stateOfFlow.value.is_loading != true) {
                        stateOfFlow.value = AppState(is_loading = true)
                    }
                }
                else if(apiResult.coffeeList != null){
                    stateOfFlow.value = AppState(coffeeList = apiResult.coffeeList, is_loading = false)
                }
                else if (apiResult.error.isNullOrBlank().not()){
                    stateOfFlow.value = AppState(is_error = apiResult.error, is_loading = false, coffeeList = null)
                }
                else if (apiResult.loading == false && apiResult.coffeeList == null && apiResult.error == null) {
                     stateOfFlow.value = AppState(is_loading = false, is_error = "No data received after loading.")
                }
            }
        }
    }
}
