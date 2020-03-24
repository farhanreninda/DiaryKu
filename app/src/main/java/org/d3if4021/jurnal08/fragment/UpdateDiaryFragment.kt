package org.d3if4021.jurnal08.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import org.d3if4021.jurnal08.MainActivity
import org.d3if4021.jurnal08.R
import org.d3if4021.jurnal08.database.DiaryDatabase
import org.d3if4021.jurnal08.databinding.FragmentUpdateDiaryBinding
import org.d3if4021.jurnal08.viewmodel.DiaryViewModel
import org.d3if4021.jurnal08.viewmodel.DiaryViewModelFactor

/**
 * A simple [Fragment] subclass.
 */
class UpdateDiaryFragment : Fragment() {

    private lateinit var binding: FragmentUpdateDiaryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        judul()
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_update_diary, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = DiaryDatabase.getInstance(application).DiaryDao
        val viewModelFactory = DiaryViewModelFactor(dataSource, application)
        val diaryViewModel = ViewModelProviders.of(this, viewModelFactory).get(DiaryViewModel::class.java)
//        DiarykuAdapter.updateItem(newList)

        binding.fabCreate.setOnClickListener {
            diaryViewModel.onClickInsert(binding.etDiary.text.toString())
            it.findNavController().navigate(R.id.action_updateDiaryFragment_to_homeFragment)
        }

        return binding.root
    }

    private fun judul() {
        val getActivity = activity!! as MainActivity
        getActivity.supportActionBar?.title = "Update Cerita"
    }

}


