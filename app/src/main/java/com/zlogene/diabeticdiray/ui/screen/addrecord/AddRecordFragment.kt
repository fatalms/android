package com.zlogene.diabeticdiray.ui.screen.addrecord

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
import com.zlogene.diabeticdiray.databinding.FragmentAddRecordBinding
import java.lang.Exception
import java.lang.NumberFormatException

class AddRecordFragment : Fragment() {
    private lateinit var viewModel: AddRecordViewModel
    private var binding: FragmentAddRecordBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Create viewModel
        val application = requireNotNull(this.activity).application
        val dao = RecordingDatabase.getInstance(application).getRecordingDatabaseDao()
        val viewModelFactory = AddRecordViewModelFactory(dao, application)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(AddRecordViewModel::class.java)

        binding = FragmentAddRecordBinding.inflate(
            layoutInflater, container, false
        )

        // Set Action bar Menu
        setHasOptionsMenu(true)

        return binding!!.root
    }

    fun correctFields(): Boolean {
        var flag = false
        try {
            if (binding!!.sugarEditText.text.toString().isNotEmpty() &&
                binding!!.insulinEditText.text.toString().isNotEmpty()&&
                binding!!.sugarEditText.text.toString().toFloatOrNull()?.equals(null) == false &&
                binding!!.insulinEditText.text.toString().toFloatOrNull()?.equals(null) == false
            ) {
                flag = true
            }
        } catch (e: Exception) {
            Toast.makeText(context, "Incorrect input", Toast.LENGTH_SHORT).show()
        }
        finally {
            return flag
        }
    }

    fun saveButtonPressed() {
        if (correctFields()) {
            viewModel.addRecord(
                binding!!.sugarEditText.text.toString(),
                binding!!.insulinEditText.text.toString(),
                binding!!.noteEditText.text.toString()
            )
            Toast.makeText(context, "added successful", Toast.LENGTH_SHORT).show()
            // Navigate Back
            findNavController().navigate(R.id.action_addRecordFragment_to_listFragment)
        } else {
            Toast.makeText(
                context,
                "Error, not all fields field or incorrect input",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_add) {
            saveButtonPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_fragment_menu, menu)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
