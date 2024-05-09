package org.d3if3056.assesment.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.d3if3056.assesment.model.Jurnal

@Database(entities = [Jurnal::class], version = 1, exportSchema = false)
abstract class JurnalDb : RoomDatabase() {
    abstract val dao: JurnalDao

    companion object{
        @Volatile
        private var INSTANCE: JurnalDb? = null

        fun getInstance(context: Context): JurnalDb {
            synchronized(this){
                var instance = INSTANCE

                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        JurnalDb::class.java,
                        "mahasiswa.db"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}