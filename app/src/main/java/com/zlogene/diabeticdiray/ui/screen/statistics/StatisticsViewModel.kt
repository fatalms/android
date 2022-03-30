package com.zlogene.diabeticdiray.ui.screen.statistics

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.zlogene.diabeticdiray.database.RecordingDAO

class StatisticsViewModel(
    val dao: RecordingDAO,
    application: Application
) : AndroidViewModel(application) {

    // Sugar Live Data
    val weekStatSugar = dao.getWeekSugar()
    val weekAVGSugar = dao.getAVGWeekSugar()

    val monthStatSugar = dao.getMonthSugar()
    val monthAVGSugar = dao.getAVGMonthSugar()

    val quarterStatSugar = dao.getQuarterSugar()
    val quarterAVGSugar = dao.getAVGQuarterSugar()

    val yearStatSugar = dao.getYearSugar()
    val yearAVGSugar = dao.getAVGYearSugar()

    // Insulin Live Data
    val weekStatInsulin = dao.getWeekInsulin()
    val weekAVGInsulin = dao.getAVGWeekInsulin()

    val monthStatInsulin = dao.getMonthInsulin()
    val monthAVGInsulin = dao.getAVGMonthInsulin()

    val quarterStatInsulin = dao.getQuarterInsulin()
    val quarterAVGInsulin = dao.getAVGQuarterInsulin()

    val yearStatInsulin = dao.getYearInsulin()
    val yearAVGInsulin = dao.getAVGYearInsulin()
}
