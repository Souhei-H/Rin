package com.rinats.rin.model.table

import com.rinats.rin.model.table.compositeId.TotalSalaryId
import javax.persistence.Column
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "total_salary")
open class TotalSalary {
    @EmbeddedId
    open var id: TotalSalaryId? = null

    @Column(name = "salary")
    open var salary: Int? = null
}