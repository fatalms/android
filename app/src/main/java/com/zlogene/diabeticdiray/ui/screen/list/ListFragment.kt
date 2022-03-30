package com.zlogene.diabeticdiray.ui.screen.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.zlogene.diabeticdiray.R
import com.zlogene.diabeticdiray.database.RecordingDatabase
import com.zlogene.diabeticdiray.databinding.FragmentListBinding

class ListFragment : Fragment() {
    private lateinit var viewModel: ListViewModel
    private var binding: FragmentListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(
            layoutInflater, container, false
        )
        // Setup Action bar menu
        setHasOptionsMenu(true)

        // Create viewModel
        val application = requireNotNull(this.activity).application
        val dao = RecordingDatabase.getInstance(application).getRecordingDatabaseDao()
        val viewModelFactory = ListViewModelFactory(dao, application)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(ListViewModel::class.java)

        // Set RecyclerView
        val adapter = ListAdapter()
        binding?.recyclerView?.adapter = adapter
        viewModel.records.observe(
            viewLifecycleOwner,
            { records ->
                if (records != null)
                    adapter.recordings = records
            }
        )

        // AddRecording button Listener
        binding?.addRecording?.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addRecordFragment)
        }

        return binding!!.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_add_test_data -> viewModel.addTestData()
            R.id.menu_delete_all -> confirmRemoval()
        }
        return super.onOptionsItemSelected(item)
    }

    // Show AlertDialog to Confirm Removal of All Items from Database Table
    private fun confirmRemoval() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            viewModel.deleteAll()
            Toast.makeText(
                requireContext(),
                "Successfully Removed Everything!",
                Toast.LENGTH_SHORT
            ).show()
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete everything?")
        builder.setMessage("Are you sure you want to remove everything?")
        builder.create().show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
