package com.example.sahabss.data.remote.mapper

import com.example.sahabss.data.remote.dto.employee.EmployeeDTO
import com.example.sahabss.data.remote.dto.employee.EmployeesDTO
import com.example.sahabss.data.remote.model.employee.Employee

object EmployeeMapper {

    fun fromEmpoyeesDTO(dto: EmployeesDTO): List<Employee>{
        return dto.data.map {
            Employee(
                id = it.id,
                employeeAge = it.employeeAge,
                employeeName = it.employeeName,
                employeeSalary = it.employeeSalary,
                profileImage = it.profileImage
            )
        }
    }

    fun fromEmpoyeeDTO(dto: EmployeeDTO): Employee{
        return Employee(
            id = dto.data.id,
            employeeAge = dto.data.employeeAge,
            employeeName = dto.data.employeeName,
            employeeSalary = dto.data.employeeSalary,
            profileImage = dto.data.profileImage
        )
    }

    fun fromupdateEmpoyeesDTO(dto: EmployeesDTO): String {
        return dto.message
    }

    fun fromdeleteEmpoyeesDTO(dto: EmployeesDTO): String{
        return dto.message
    }

}