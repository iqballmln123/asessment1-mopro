package org.d3if3056.assesment.ui.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if3056.assesment.network.SkincareApi

class KoleksiViewModel : ViewModel() {
    init {
        retrieveData()
    }

    private fun retrieveData(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = SkincareApi.service.getSkincare()
                Log.d("KoleksiViewModel", "Success: $result")
            } catch (e: Exception){
                Log.d("KoleksiViewModel", "Failure: ${e.message}")
            }
        }
    }
}