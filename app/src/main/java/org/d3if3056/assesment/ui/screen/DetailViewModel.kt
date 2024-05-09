package org.d3if3056.assesment.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if3056.assesment.database.JurnalDao
import org.d3if3056.assesment.model.Jurnal
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DetailViewModel(private val dao: JurnalDao) : ViewModel() {
    private val formatter = SimpleDateFormat("yyy-MM-dd HH:mm:ss", Locale.US)

    fun insert(kondisi_kulit: String, rutinitas: String, moods: String, notes: String, steps: String, extra_steps: String){
        val jurnal = Jurnal(
            tanggal = formatter.format(Date()),
            kondisi_kulit = kondisi_kulit,
            rutinitas = rutinitas,
            moods = moods,
            notes = notes,
            steps = steps,
            extra_steps = extra_steps
        )
        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(jurnal)
        }
    }
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