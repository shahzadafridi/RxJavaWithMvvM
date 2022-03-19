package com.example.sahabss.data.remote.repository

import com.example.sahabss.data.remote.model.employee.Employee
import com.example.sahabss.util.UiStateResource
import io.reactivex.Maybe
import io.reactivex.disposables.CompositeDisposable

interface EmployeeRepository {

    fun getEmployees(
        compositeDisposable: CompositeDisposable,
        onResponse: (UiStateResource<List<Employee>>) -> Unit
    )

    fun getEmployee(
        compositeDisposable: CompositeDisposable,
        id: String,
        onResponse: (UiStateResource<Employee>) -> Unit
    )

    fun updateEmployee(
        compositeDisposable: CompositeDisposable,
        id: String,
        onResponse: (UiStateResource<String>) -> Unit
    )

    fun deleteEmployee(
        compositeDisposable: CompositeDisposable,
        id: String,
        onResponse: (UiStateResource<String>) -> Unit
    )
}