package org.d3if4021.jurnal08.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import org.d3if4021.jurnal08.MainActivity
import org.d3if4021.jurnal08.R
import org.d3if4021.jurnal08.database.DiaryDatabase
import org.d3if4021.jurnal08.databinding.FragmentHomeBinding
import org.d3if4021.jurnal08.recyclerview.DiarykuAdapter
import org.d3if4021.jurnal08.viewmodel.DiaryViewModel
import org.d3if4021.jurnal08.viewmodel.DiaryViewModelFactor

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        judul()
        setHasOptionsMenu(true)
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_home, container, false)

        // fab action
        binding.fab1.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_tambahDiaryFragment)
        }

        val application = requireNotNull(this.activity).application
        val dataSource = DiaryDatabase.getInstance(application).DiaryDao
        val viewModelFactory = DiaryViewModelFactor(dataSource, application)
        val diaryViewModel = ViewModelProviders.of(this, viewModelFactory).get(DiaryViewModel::class.java)

        diaryViewModel.diary.observe(viewLifecycleOwner, Observer {
            val recyclerView = binding.diaryku
            val adapter = DiarykuAdapter(it)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        })

        binding.setLifecycleOwner(this)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val application = requireNotNull(this.activity).application
        val dataSource = DiaryDatabase.getInstance(application).DiaryDao
        val viewModelFactory = DiaryViewModelFactor(dataSource, application)
        val diaryViewModel = ViewModelProviders.of(this, viewModelFactory).get(DiaryViewModel::class.java)

        return when (item.itemId) {
            R.id.hapus_diary -> {
                diaryViewModel.onClickClear()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun judul() {
        val getActivity = activity!! as MainActivity
        getActivity.supportActionBar?.title = "Diary Saya || 6706184021"
    }
}
