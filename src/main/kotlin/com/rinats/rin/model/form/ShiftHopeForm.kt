package com.rinats.rin.model.form

import javax.validation.constraints.NotNull

data class ShiftHopeForm(
    @field:NotNull
    val year: Int?,
    @field:NotNull
    val month: Int?,
    @field:NotNull
    val days: List<Int>?
)