package org.d3if4021.jurnal08.diffutil

import androidx.recyclerview.widget.DiffUtil
import org.d3if4021.jurnal08.viewmodel.DiaryViewModel

class DiffCallback (
    private val mOldList: List<DiaryViewModel>,
    private val mNewList: List<DiaryViewModel>) : DiffUtil.Callback(){

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldList[oldItemPosition].diary == mNewList[newItemPosition].diary
    }

    override fun getOldListSize(): Int {
        return mOldList.size
    }

    override fun getNewListSize(): Int {
        return mNewList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldList[oldItemPosition].diary == mNewList[newItemPosition].diary
    }
}