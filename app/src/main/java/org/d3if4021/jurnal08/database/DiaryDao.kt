package org.d3if4021.jurnal08.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DiaryDao {
    @Insert
    fun insert(diary: Diary)

    @Query("SELECT * FROM diary")
    fun getDiary(): LiveData<List<Diary>>

    @Query("DELETE FROM diary")
    fun clear()
}