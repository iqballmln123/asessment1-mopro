package org.d3if3056.assesment.ui.screen

import androidx.lifecycle.ViewModel
import org.d3if3056.assesment.model.Jurnal

class DetailViewModel : ViewModel() {
    fun getJurnal(id: Long): Jurnal{
        return Jurnal(
            id,
            "Kemerah-merahan",
            "Pagi",
            "Luar Biasa",
            "none",
            "Cleanser",
            "none",
            "2024-05-$id"
        )
    }
}