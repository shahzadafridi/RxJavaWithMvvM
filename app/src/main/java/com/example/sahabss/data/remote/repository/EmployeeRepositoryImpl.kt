package com.example.sahabss.data.remote.repository

import com.example.sahabss.data.remote.dto.employee.EmployeeDTO
import com.example.sahabss.data.remote.dto.employee.delete.DeleteEmployeeDTO
import com.example.sahabss.data.remote.dto.employee.update.UpdateEmployeeDTO
import com.example.sahabss.data.remote.mapper.EmployeeMapper
import com.example.sahabss.data.remote.model.employee.Employee
import com.example.sahabss.data.remote.network.EmployeeApiService
import com.example.sahabss.data.util.toNetworkResourceFailure
import com.example.sahabss.util.UiStateResource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class EmployeeRepositoryImpl @Inject constructor(
    private val apiService: EmployeeApiService
) : EmployeeRepository {

    override fun getEmployees(
        compositeDisposable: CompositeDisposable,
        onResponse: (UiStateResource<List<Employee>>) -> Unit
    ) {
        compositeDisposable.add(
            apiService.getEmployees()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        onResponse.invoke(
                            UiStateResource.Success(EmployeeMapper.fromEmpoyeesDTO(it))
                        )
                    },
                    {
                        onResponse.invoke(
                            it.toNetworkResourceFailure()
                        )
                    })
        )
    }

    override fun getEmployee(
        compositeDisposable: CompositeDisposable,
        id: Int,
        onResponse: (UiStateResource<Employee>) -> Unit
    ) {
        compositeDisposable.add(
            apiService.getEmployee(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<EmployeeDTO>() {
                    override fun onSuccess(data: EmployeeDTO) {
                        UiStateResource.Success(EmployeeMapper.fromEmpoyeeDTO(data))
                    }

                    override fun onError(e: Throwable) {
                        onResponse.invoke(
                            e.toNetworkResourceFailure()
                        )
                    }
                })
        )
    }

    override fun updateEmployee(
        compositeDisposable: CompositeDisposable,
        id: Int,
        onResponse: (UiStateResource<String>) -> Unit
    ) {
        compositeDisposable.add(
            apiService.updateEmployees(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<UpdateEmployeeDTO>() {
                    override fun onSuccess(data: UpdateEmployeeDTO) {
                        UiStateResource.Success(EmployeeMapper.fromupdateEmpoyeesDTO(data))
                    }

                    override fun onError(e: Throwable) {
                        onResponse.invoke(
                            e.toNetworkResourceFailure()
                        )
                    }
                })
        )
    }

    override fun deleteEmployee(
        compositeDisposable: CompositeDisposable,
        id: Int,
        onResponse: (UiStateResource<String>) -> Unit
    ) {
        compositeDisposable.add(
            apiService.deleteEmployees(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<DeleteEmployeeDTO>() {
                    override fun onSuccess(data: DeleteEmployeeDTO) {
                        UiStateResource.Success(EmployeeMapper.fromdeleteEmpoyeesDTO(data))
                    }

                    override fun onError(e: Throwable) {
                        onResponse.invoke(
                            e.toNetworkResourceFailure()
                        )
                    }
                })
        )
    }
}