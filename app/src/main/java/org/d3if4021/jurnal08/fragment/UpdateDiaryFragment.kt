package org.d3if4021.jurnal08.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import org.d3if4021.jurnal08.MainActivity
import org.d3if4021.jurnal08.R
import org.d3if4021.jurnal08.database.Diary
import org.d3if4021.jurnal08.database.DiaryDatabase
import org.d3if4021.jurnal08.databinding.FragmentUpdateDiaryBinding
import org.d3if4021.jurnal08.viewmodel.DiaryViewModel
import org.d3if4021.jurnal08.viewmodel.DiaryViewModelFactor

class UpdateDiaryFragment : Fragment() {

    private lateinit var binding: FragmentUpdateDiaryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        judul()
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_update_diary, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {
            val message = arguments!!.getString("message")

            binding.etDiary.setText(message)
        }

        val application = requireNotNull(this.activity).application
        val dataSource = DiaryDatabase.getInstance(application).DiaryDao
        val viewModelFactory = DiaryViewModelFactor(dataSource, application)
        val diaryViewModel = ViewModelProvider(this, viewModelFactory).get(DiaryViewModel::class.java)

        binding.fabUpdate.setOnClickListener {
            inputCheck(arguments!!.getLong("id"), diaryViewModel)
            (binding.etDiary.text.toString())
            it.findNavController().navigate(R.id.action_updateDiaryFragment_to_homeFragment)
        }

    }

    private fun inputCheck(id: Long, diaryViewModel: DiaryViewModel): Boolean {
        return when {
            binding.etDiary.text.trim().isEmpty() -> false
            else -> {
                doUpdate(id, diaryViewModel)
                true
            }
        }
    }

    private fun doUpdate(id: Long, diaryViewModel: DiaryViewModel) {
        val message = binding.etDiary.text.toString()
        val date = System.currentTimeMillis()
        val diary = Diary(id, message, date)
        diaryViewModel.onClickUpdate(diary)
    }

    private fun judul() {
        val getActivity = activity!! as MainActivity
        getActivity.supportActionBar?.title = "Update Cerita"
    }

}


