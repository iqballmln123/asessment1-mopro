package org.d3if3056.assesment.ui.screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.d3if3056.assesment.model.Skincare
import org.d3if3056.assesment.network.ApiSatus
import org.d3if3056.assesment.network.SkincareApi

class KoleksiViewModel : ViewModel() {
    var data = mutableStateOf(emptyList<Skincare>())
        private set

    var status = MutableStateFlow(ApiSatus.LOADING)
        private set

    init {
        retrieveData()
    }

    fun retrieveData(){
        viewModelScope.launch(Dispatchers.IO) {
            status.value = ApiSatus.LOADING
            try {
                data.value = SkincareApi.service.getSkincare()
                status.value = ApiSatus.SUCCESS
            } catch (e: Exception){
                Log.d("KoleksiViewModel", "Failure: ${e.message}")
                status.value = ApiSatus.FAILED
            }
        }
    }
}