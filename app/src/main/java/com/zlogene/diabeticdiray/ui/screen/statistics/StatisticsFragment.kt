package com.zlogene.diabeticdiray.ui.screen.statistics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.zlogene.diabeticdiray.database.RecordingDatabase
import com.zlogene.diabeticdiray.databinding.FragmentStatisticsBinding

class StatisticsFragment : Fragment() {
    private lateinit var viewModel: StatisticsViewModel
    private var binding: FragmentStatisticsBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Create viewModel
        val application = requireNotNull(this.activity).application
        val dao = RecordingDatabase.getInstance(application).getRecordingDatabaseDao()
        val viewModelFactory = StatisticsViewModelFactory(dao, application)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(StatisticsViewModel::class.java)

        binding = FragmentStatisticsBinding.inflate(
            layoutInflater, container, false
        )
        // Binding data in layout
        bindData()

        return binding!!.root
    }

    private fun bindData() {
        bindDataInSugarWeek()
        bindDataInSugarMonth()
        bindDataInSugarQuarter()
        bindDataInSugarYear()

        bindDataInInsulinWeek()
        bindDataInInsulinMonth()
        bindDataInInsulinQuarter()
        bindDataInInsulinYear()
    }

    private fun bindDataInInsulinYear() {
        viewModel.yearStatInsulin.observe(
            viewLifecycleOwner,
            {
                binding?.insulinYearMinTextView?.text = it.first().insulin.toString()
                binding?.insulinYearMaxTextView?.text = it.last().insulin.toString()
            }
        )
        viewModel.yearAVGInsulin.observe(
            viewLifecycleOwner,
            {
                binding?.insulinYearAvgTextView?.text = it.toString()
            }
        )
    }

    private fun bindDataInInsulinQuarter() {
        viewModel.quarterStatInsulin.observe(
            viewLifecycleOwner,
            {
                binding?.insulinQuarterMinTextView?.text = it.first().insulin.toString()
                binding?.insulinQuarterMaxTextView?.text = it.last().insulin.toString()
            }
        )
        viewModel.quarterAVGInsulin.observe(
            viewLifecycleOwner,
            {
                binding?.insulinQuarterAvgTextView?.text = it.toString()
            }
        )
    }

    private fun bindDataInInsulinMonth() {
        viewModel.monthStatInsulin.observe(
            viewLifecycleOwner,
            {
                binding?.insulinMonthMinTextView?.text = it.first().insulin.toString()
                binding?.insulinMonthMaxTextView?.text = it.last().insulin.toString()
            }
        )
        viewModel.monthAVGInsulin.observe(
            viewLifecycleOwner,
            {
                binding?.insulinMonthAvgTextView?.text = it.toString()
            }
        )
    }

    private fun bindDataInInsulinWeek() {
        viewModel.weekStatInsulin.observe(
            viewLifecycleOwner,
            {
                binding?.insulinWeekMinTextView?.text = it.first().insulin.toString()
                binding?.insulinWeekMaxTextView?.text = it.last().insulin.toString()
            }
        )
        viewModel.weekAVGInsulin.observe(
            viewLifecycleOwner,
            {
                binding?.insulinWeekAvgTextView?.text = it.toString()
            }
        )
    }

    private fun bindDataInSugarYear() {
        viewModel.yearStatSugar.observe(
            viewLifecycleOwner,
            {
                binding?.yearMinTextView?.text = it.first().sugar.toString()
                binding?.yearMaxTextView?.text = it.last().sugar.toString()
            }
        )
        viewModel.yearAVGSugar.observe(
            viewLifecycleOwner,
            {
                binding?.yearAvgTextView?.text = it.toString()
            }
        )
    }

    private fun bindDataInSugarQuarter() {
        viewModel.quarterStatSugar.observe(
            viewLifecycleOwner,
            {
                binding?.quarterMinTextView?.text = it.first().sugar.toString()
                binding?.quarterMaxTextView?.text = it.last().sugar.toString()
            }
        )
        viewModel.quarterAVGSugar.observe(
            viewLifecycleOwner,
            {
                binding?.quarterAvgTextView?.text = it.toString()
            }
        )
    }

    private fun bindDataInSugarMonth() {
        viewModel.monthStatSugar.observe(
            viewLifecycleOwner,
            {
                binding?.monthMinTextView?.text = it.first().sugar.toString()
                binding?.monthMaxTextView?.text = it.last().sugar.toString()
            }
        )
        viewModel.monthAVGSugar.observe(
            viewLifecycleOwner,
            {
                binding?.monthAvgTextView?.text = it.toString()
            }
        )
    }

    private fun bindDataInSugarWeek() {
        viewModel.weekStatSugar.observe(
            viewLifecycleOwner,
            {
                binding?.weekMinTextView?.text = it.first().sugar.toString()
                binding?.weekMaxTextView?.text = it.last().sugar.toString()
            }
        )
        viewModel.weekAVGSugar.observe(
            viewLifecycleOwner,
            {
                binding?.weekAvgTextView?.text = it.toString()
            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
