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
    private val diary: List<Diary>) : RecyclerView.Adapter<DiarykuAdapter.DiaryViewHolder>() {

    var listener: RecyclerViewClickListener? = null

    inner class DiaryViewHolder(
        val recyclerviewDiaryBinding: RecyclerViewDiarykuBinding
    ) : RecyclerView.ViewHolder(recyclerviewDiaryBinding.root)

    override fun getItemCount() = diary.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DiaryViewHolder(

        DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.recycler_view_diaryku, parent, false)
    )

    override fun onBindViewHolder(holder: DiaryViewHolder, position: Int) {
        holder.recyclerviewDiaryBinding.tanggal.text = convertLongToDateString(diary[position].tanggal)
        holder.recyclerviewDiaryBinding.isi.text = diary[position].message
        holder.recyclerviewDiaryBinding.listDiary.setOnClickListener {
            listener?.onRecyclerViewItemClicked(it, diary[position])
        }
    }
}

