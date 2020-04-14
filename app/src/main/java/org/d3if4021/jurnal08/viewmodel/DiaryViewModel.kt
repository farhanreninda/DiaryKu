package org.d3if4021.jurnal08.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.*
import org.d3if4021.jurnal08.database.Diary
import org.d3if4021.jurnal08.database.DiaryDao

class DiaryViewModel(
    val database: DiaryDao,
    application: Application
) :AndroidViewModel(application) {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    val diary = database.getDiary()

    fun onClickInsert(message: String) {
        uiScope.launch {
            val diary = Diary(0, message)
            insert(diary)

        }
    }

    private suspend fun insert(diary: Diary) {
        withContext(Dispatchers.IO) {
            database.insert(diary)
        }
    }

    fun onClickClear() {
        uiScope.launch {
            clear()
        }
    }

    private suspend fun clear() {
        withContext(Dispatchers.IO) {
            database.clear()
        }
    }

    fun onClickUpdate(diary: Diary) {
        uiScope.launch {
            update(diary)
        }
    }

    private suspend fun update(diary: Diary) {
        withContext(Dispatchers.IO) {
            database.update(diary)
        }
    }

    fun onClickDelete(diaryId: Long) {
        uiScope.launch {
            delete(diaryId)
        }
    }

    private suspend fun delete(diaryId: Long) {
        withContext(Dispatchers.IO) {
            database.delete(diaryId)
        }
    }
}