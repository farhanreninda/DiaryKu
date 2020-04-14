package org.d3if4021.jurnal08.fragment

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import org.d3if4021.jurnal08.MainActivity
import org.d3if4021.jurnal08.R
import org.d3if4021.jurnal08.database.Diary
import org.d3if4021.jurnal08.database.DiaryDatabase
import org.d3if4021.jurnal08.databinding.FragmentHomeBinding
import org.d3if4021.jurnal08.recyclerview.DiarykuAdapter
import org.d3if4021.jurnal08.recyclerview.RecyclerViewClickListener
import org.d3if4021.jurnal08.viewmodel.DiaryViewModel
import org.d3if4021.jurnal08.viewmodel.DiaryViewModelFactor

class HomeFragment : Fragment(), RecyclerViewClickListener {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        judul()
        setHasOptionsMenu(true)
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_home, container, false)

        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val application = requireNotNull(this.activity).application
        val dataSource = DiaryDatabase.getInstance(application).DiaryDao
        val viewModelFactory = DiaryViewModelFactor(dataSource, application)
        val diaryViewModel = ViewModelProvider(this, viewModelFactory).get(DiaryViewModel::class.java)

        diaryViewModel.diary.observe(viewLifecycleOwner, Observer {
            val adapter = DiarykuAdapter(it)
            val recyclerView = binding.diaryku
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this.requireContext())

            adapter.listener = this
        })

        binding.fab1.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_tambahDiaryFragment)
        }

    }

    override fun onRecyclerViewItemClicked(view: View, diary: Diary) {

        when(view.id) {
            R.id.list_diary -> {
                val bundle = Bundle()
                bundle.putLong("id", diary.id)
                bundle.putString("message", diary.message)
                view.findNavController().navigate(R.id.action_homeFragment_to_updateDiaryFragment, bundle)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val application = requireNotNull(this.activity).application
        val dataSource = DiaryDatabase.getInstance(application).DiaryDao
        val viewModelFactory = DiaryViewModelFactor(dataSource, application)
        val diaryViewModel = ViewModelProvider(this, viewModelFactory).get(DiaryViewModel::class.java)

        return when (item.itemId) {
            R.id.hapus_diary -> {
                diaryViewModel.onClickClear()
                Toast.makeText(requireContext(), R.string.success_remove, Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.dark_mode -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                Toast.makeText(requireContext(), R.string.dark_mode_success, Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.light_mode -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                Toast.makeText(requireContext(), R.string.light_mode_success, Toast.LENGTH_SHORT).show()
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
