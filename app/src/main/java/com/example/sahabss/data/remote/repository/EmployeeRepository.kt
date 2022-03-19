package com.example.sahabss.data.remote.repository

import com.example.sahabss.data.remote.model.employee.Employee
import com.example.sahabss.util.UiStateResource
import io.reactivex.Maybe
import io.reactivex.disposables.CompositeDisposable
import retrofit2.http.Body

interface EmployeeRepository {

    fun getEmployees(
        compositeDisposable: CompositeDisposable,
        onResponse: (UiStateResource<List<Employee>>) -> Unit
    )

    fun getEmployee(
        compositeDisposable: CompositeDisposable,
        id: Int,
        onResponse: (UiStateResource<Employee>) -> Unit
    )

    fun updateEmployee(
        compositeDisposable: CompositeDisposable,
        id: Int,
        payload: Map<String,String?>,
        onResponse: (UiStateResource<String>) -> Unit
    )

    fun deleteEmployee(
        compositeDisposable: CompositeDisposable,
        id: Int,
        onResponse: (UiStateResource<String>) -> Unit
    )
}