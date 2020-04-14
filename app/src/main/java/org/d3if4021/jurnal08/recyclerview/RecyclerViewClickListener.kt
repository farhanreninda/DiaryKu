package org.d3if4021.jurnal08.recyclerview

import android.view.View
import org.d3if4021.jurnal08.database.Diary

interface RecyclerViewClickListener {
    fun onRecyclerViewItemClicked(view: View, diary: Diary)
}