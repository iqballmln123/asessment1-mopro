package org.d3if3056.assesment.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jurnal")
data class Jurnal(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val kondisi_kulit: String,
    val rutinitas: String,
    val moods: String,
    val notes: String,
    val steps: String,
    val extra_steps: String,
    val tanggal: String
)
