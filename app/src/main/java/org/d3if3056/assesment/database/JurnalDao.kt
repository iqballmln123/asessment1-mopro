package org.d3if3056.assesment.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.d3if3056.assesment.model.Jurnal

@Dao
interface JurnalDao {
    @Insert
    suspend fun insert(jurnal: Jurnal)

    @Update
    suspend fun update(jurnal: Jurnal)

    @Query("SELECT * FROM jurnal ORDER BY tanggal DESC")
    fun getJurnal(): Flow<List<Jurnal>>
}