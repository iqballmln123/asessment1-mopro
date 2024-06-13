package org.d3if3056.assesment.ui.screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if3056.assesment.model.Skincare
import org.d3if3056.assesment.network.SkincareApi

class KoleksiViewModel : ViewModel() {
    var data = mutableStateOf(emptyList<Skincare>())
        private set

    init {
        retrieveData()
    }

    private fun retrieveData(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                data.value = SkincareApi.service.getSkincare()
            } catch (e: Exception){
                Log.d("KoleksiViewModel", "Failure: ${e.message}")
            }
        }
    }
}