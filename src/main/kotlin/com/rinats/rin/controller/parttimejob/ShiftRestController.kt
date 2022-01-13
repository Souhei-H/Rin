package com.rinats.rin.controller.parttimejob

import com.rinats.rin.model.form.GetShiftsForm
import com.rinats.rin.model.response.ShiftResponse
import com.rinats.rin.service.ShiftService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/shift")
class ShiftRestController(
    @Autowired
    val shiftService: ShiftService,
) {
    @GetMapping("/shift_table_check")
    fun checkShiftTable(
        @Validated getShiftsForm: GetShiftsForm,
        bindingResult: BindingResult
    ): ShiftResponse? {
        if (bindingResult.hasErrors()) {
            return null
        }
        return shiftService.getShift(getShiftsForm.year, getShiftsForm.month)
    }
}