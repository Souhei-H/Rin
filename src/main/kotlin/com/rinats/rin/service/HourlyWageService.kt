package com.rinats.rin.service

import com.rinats.rin.model.Employee
import com.rinats.rin.repository.EmployeeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class HourlyWageService(
    @Autowired
    private val employeeRepository: EmployeeRepository
) {

    fun getHourlyWage(): MutableList<Employee> {
        return employeeRepository.findAll()
    }

    fun hourlyWageUpdate(employeeId: String, hourlyWage: Int) {
        if (employeeRepository.findById(employeeId).isEmpty) {
            val employee = employeeRepository.findById(employeeId).get()
            val wage = Employee(
                employee.employeeId,
                employee.firstName,
                employee.lastName,
                employee.gender,
                employee.birthday,
                hourlyWage,
                employee.isAndroid,
                employee.roleId
            )
            employeeRepository.save(wage)
        }
    }
}