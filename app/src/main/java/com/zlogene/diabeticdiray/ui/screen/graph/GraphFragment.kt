package com.zlogene.diabeticdiray.ui.screen.graph

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.components.YAxis.AxisDependency
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.zlogene.diabeticdiray.databinding.FragmentGraphBinding
import com.zlogene.diabeticdiray.model.RecordingEntity
import java.text.SimpleDateFormat
import java.util.*

class GraphFragment : Fragment() {
    private var binding: FragmentGraphBinding? = null
    private val viewModel: GraphViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentGraphBinding.inflate(
            layoutInflater, container, false
        )

        viewModel.records.observe(viewLifecycleOwner, { updateGraph(it) })

        return binding!!.root
    }

    class MyXAxisFormatter : ValueFormatter() {
        @SuppressLint("SimpleDateFormat")
        private val mFormat = SimpleDateFormat("dd MMM HH:mm", Locale.ENGLISH)
        override fun getAxisLabel(value: Float, axis: AxisBase?): String? {
            return mFormat.format(Date(value.toLong()))
        }
    }

    private fun updateGraph(it: MutableList<RecordingEntity>?) {
        // Set Graph
        val chart = binding?.chart as LineChart

        // redraw
        chart.invalidate()


        // enable touch gestures
        chart.setTouchEnabled(true)
        chart.dragDecelerationFrictionCoef = 0.9f


        // enable scaling and dragging
        chart.isDragEnabled = true
        chart.setScaleEnabled(true)
        chart.setDrawGridBackground(false)
        chart.isHighlightPerDragEnabled = true

        // set an alternative background color
        chart.setBackgroundColor(Color.WHITE)
        chart.setViewPortOffsets(0f, 0f, 0f, 0f)


        // get the legend (only possible after setting data)
        val l = chart.legend
        l.isEnabled = true

        val xAxis = chart.xAxis
        xAxis.position = XAxis.XAxisPosition.TOP_INSIDE
        xAxis.textSize = 10f
        xAxis.setDrawAxisLine(false)
        xAxis.setDrawGridLines(true)
        xAxis.textColor = Color.BLACK
        xAxis.setCenterAxisLabels(true)
        xAxis.valueFormatter = MyXAxisFormatter()


        val leftAxis = chart.axisLeft
        leftAxis.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART)
        leftAxis.textColor = ColorTemplate.getHoloBlue()
        leftAxis.setDrawGridLines(true)
        leftAxis.isGranularityEnabled = true
        leftAxis.axisMinimum = 0f
        leftAxis.axisMaximum = 12f
        leftAxis.yOffset = -9f
        leftAxis.textColor = Color.BLACK

        val rightAxis = chart.axisRight
        rightAxis.isEnabled = false




        if (it != null) {
            val valuesSugar = ArrayList<Entry>()
            for (value in it) {
                valuesSugar.add(Entry((value.date * 1000).toFloat(), value.sugar))
            }
            val valuesInsulin = ArrayList<Entry>()
            for (value in it) {
                valuesInsulin.add(Entry((value.date * 1000).toFloat(), value.insulin))
            }

            // create a datasetSugar and give it a type
            val setSugar = LineDataSet(valuesSugar, "Sugar")
            setSugar.axisDependency = AxisDependency.LEFT
            setSugar.valueTextColor = ColorTemplate.getHoloBlue()
            setSugar.lineWidth = 3.0f
            setSugar.setDrawCircles(false)
            setSugar.setDrawValues(false)
            setSugar.color = Color.RED
            setSugar.setDrawCircleHole(false)

            // create a datasetInsulin and give it a type
            val setInsulin = LineDataSet(valuesInsulin, "Insulin")
            setInsulin.axisDependency = AxisDependency.LEFT
            setInsulin.color = ColorTemplate.getHoloBlue()
            setInsulin.valueTextColor = ColorTemplate.getHoloBlue()
            setInsulin.lineWidth = 3.0f
            setInsulin.setDrawCircles(false)
            setInsulin.setDrawValues(false)
            setInsulin.fillAlpha = 65
            setSugar.color = Color.BLUE
            setInsulin.highLightColor = Color.BLUE
            setInsulin.setDrawCircleHole(false)


            // create a data object with the data sets
            val data = LineData(setSugar, setInsulin)
            data.setValueTextColor(Color.BLACK)
            data.setValueTextSize(9f)

            // set data
            chart.data = data

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
