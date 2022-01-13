package com.rinats.rin.model

import java.time.LocalDate

data class TentativeShiftData(
    val employeeIdList: MutableList<String>,
    val shiftDate: LocalDate,
    val roleId: Int,
    val isLaborInsufficient: Boolean,
    val isNumOfPeopleInsufficient: Boolean
)
