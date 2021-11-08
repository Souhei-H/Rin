package com.rinats.rin.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "employee_level")
data class EmployeeLevel(
    @Id
    @Column(name = "employee_id")
    val employeeId: String,
    val level: Int
)