package org.d3if4021.jurnal08.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import org.d3if4021.jurnal08.R
import org.d3if4021.jurnal08.database.Diary
import org.d3if4021.jurnal08.databinding.RecyclerViewDiarykuBinding
import org.d3if4021.jurnal08.utils.convertLongToDateString

class DiarykuAdapter(
    private val diary: List<Diary>) : RecyclerView.Adapter<DiarykuAdapter.ViewHolder>(){

    inner class ViewHolder(
        val recyclerViewDiaryBinding: RecyclerViewDiarykuBinding
    ) : RecyclerView.ViewHolder(recyclerViewDiaryBinding.root)

    override fun getItemCount() = diary.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder (
        DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.recycler_view_diaryku,parent,false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.recyclerViewDiaryBinding.tanggal.text = convertLongToDateString(diary[position].tanggal)
        holder.recyclerViewDiaryBinding.isi.text = diary[position].message
    }
}
//    }
//    fun updateItem(newItems: List<DiaryViewModel>){
//        val diffResult = DiffUtil.calculateDiff(DiffCallback(data, newItems))
//        data = newItems
//        diffResult.dispatchUpdatesTo(this)
//    }
//}

//class DiffCallback : DiffUtil.ItemCallback<Diary>(){
//
//    override fun areItemsTheSame(oldItemPosition: Diary, newItemPosition: Diary): Boolean {
//        return oldItemPosition.diary == newItemPosition.diary
//    }
//
//    override fun areContentsTheSame(oldItemPosition: Diary, newItemPosition: Diary): Boolean {
//        return oldItemPosition == newItemPosition
//    }
//}

