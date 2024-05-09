package org.d3if3056.assesment.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import org.d3if3056.assesment.database.JurnalDao
import org.d3if3056.assesment.model.Jurnal

class JurnalViewModel(dao: JurnalDao) : ViewModel() {
    val data : StateFlow<List<Jurnal>> = dao.getJurnal().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )

    private fun getDataDummy(): List<Jurnal>{
        val data = mutableListOf<Jurnal>()

        for (i in 5 downTo 1){
            data.add(
                Jurnal(
                    i.toLong(),
                    "Kemerah-merahan",
                    "Pagi",
                    "Excelent",
                    "none",
                    "cleanser",
                    "none",
                    "2024-05-$i"
                )
            )
        }
        return data
    }
}