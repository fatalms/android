package com.zlogene.diabeticdiray.ui.screen.list.detail

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
import androidx.navigation.fragment.navArgs
import com.zlogene.diabeticdiray.R
import com.zlogene.diabeticdiray.database.RecordingDatabase
import com.zlogene.diabeticdiray.databinding.FragmentDetailBinding
import com.zlogene.diabeticdiray.model.RecordingEntity
import com.zlogene.diabeticdiray.util.CustomDataConverter

class DetailFragment : Fragment() {
    private val args by navArgs<DetailFragmentArgs>()

    private var binding: FragmentDetailBinding? = null
    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // set menu
        setHasOptionsMenu(true)

        // Create viewModel
        val application = requireNotNull(this.activity).application
        val dao = RecordingDatabase.getInstance(application).getRecordingDatabaseDao()
        val viewModelFactory = DetailViewModelFactory(dao, application)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(DetailViewModel::class.java)

        binding = FragmentDetailBinding.inflate(
            layoutInflater, container, false
        )

        binding?.currentDateTextView?.text = CustomDataConverter.getDate(args.currentItem.date, "EEE MMM dd YYYY HH:mm ")
        binding?.currentSugarTextView?.text = args.currentItem.sugar.toString()
        binding?.currentInsulinTextView?.text = args.currentItem.insulin.toString()
        binding?.currentNoteEditText?.setText(args.currentItem.textNote)

        return binding!!.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_save -> updateItem()
            R.id.menu_delete -> confirmItemRemoval()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun confirmItemRemoval() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            viewModel.deleteRecord(args.currentItem)
            Toast.makeText(
                requireContext(),
                "Successfully Removed record!",
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_detailFragment_to_listFragment)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete record?")
        builder.setMessage("Are you sure you want to remove this record?")
        builder.create().show()
    }

    private fun updateItem() {
        val note = binding?.currentNoteEditText?.text.toString()

        val updateItem = RecordingEntity(
            args.currentItem.recordingId,
            args.currentItem.date,
            args.currentItem.sugar,
            args.currentItem.insulin,
            note
        )
        // Update current Item
        viewModel.updateRecord(updateItem)
        Toast.makeText(context, "Successfully updated!", Toast.LENGTH_SHORT).show()
        // Navigate Back
        findNavController().navigate(R.id.action_detailFragment_to_listFragment)
    }
}
