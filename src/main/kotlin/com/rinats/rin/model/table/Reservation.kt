package com.rinats.rin.model.table

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "reservation")
data class Reservation (
    @Id
    val id: String,
    @Column(name = "customer_name")
    val customerName: String,
    @Column(name = "course_id")
    val courseId: String,
    @Column(name = "date_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    val dateTime: LocalDateTime,
    @Column(name = "num_of_people")
    val numOfPeople: Int,
    @Column(name = "employee_id")
    val employeeId: String,
    @Column(name = "table_name")
    val tableName: String
)