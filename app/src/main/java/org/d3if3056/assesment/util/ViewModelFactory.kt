package org.d3if3056.assesment.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if3056.assesment.database.JurnalDao
import org.d3if3056.assesment.ui.screen.JurnalViewModel

class ViewModelFactory(
    private val dao: JurnalDao
) : ViewModelProvider.Factory{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(JurnalViewModel::class.java)){
            return JurnalViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}