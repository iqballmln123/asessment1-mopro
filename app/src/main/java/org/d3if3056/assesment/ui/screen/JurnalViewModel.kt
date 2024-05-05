package org.d3if3056.assesment.ui.screen

import androidx.lifecycle.ViewModel
import org.d3if3056.assesment.model.Jurnal

class JurnalViewModel : ViewModel() {
    val data = getDataDummy()

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