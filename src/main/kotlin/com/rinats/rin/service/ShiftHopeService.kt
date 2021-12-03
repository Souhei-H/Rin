package com.rinats.rin.service

import com.rinats.rin.model.ShiftHope
import com.rinats.rin.model.form.ShiftHopeForm
import com.rinats.rin.model.response.ShiftHopeResponse
import com.rinats.rin.repository.ShiftHopeRepository
import com.rinats.rin.util.DateUtil
import org.springframework.beans.factory.annotation.Autowired
import java.sql.Date
import java.text.ParseException
import java.util.*
import kotlin.collections.ArrayList

class ShiftHopeService(
    @Autowired
    val shiftHopeRepository: ShiftHopeRepository
) {
    fun getShiftHope(employeeId: String, year: Int, month: Int): ShiftHopeResponse {
        val shiftHopes = shiftHopeRepository.findByEmployeeId(employeeId)
        val days = ArrayList<Int>()
        shiftHopes.forEach {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = it.date.time
            val y = calendar.get(Calendar.YEAR)
            val m = calendar.get(Calendar.MONTH)
            if (year == y && month == m) {
                days.add(calendar.get(Calendar.DAY_OF_MONTH))
            }
        }

        return ShiftHopeResponse(year, month, days)
    }

    fun submitShift(shiftHopeForm: ShiftHopeForm, employeeId: String): Boolean {
        val year = shiftHopeForm.year
        val month = shiftHopeForm.month

        shiftHopeForm.days.forEach { day ->
            val date = try {
                DateUtil.getDate(year, month, day)
            } catch (e: ParseException) {
                return false
            }

            println(date)
            val shiftHope = ShiftHope(
                Date(date.time),
                employeeId
            )
            shiftHopeRepository.save(shiftHope)
        }
        return true
    }

    fun deleteShiftHope(employeeId: String): Boolean {
        return shiftHopeRepository.deleteByEmployeeId(employeeId)
    }
}