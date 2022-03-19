package com.example.sahabss.data.repository


import com.example.sahabss.data.remote.mapper.EmployeeMapper
import com.example.sahabss.data.remote.model.employee.Employee
import com.example.sahabss.data.remote.network.EmployeeApiService
import com.example.sahabss.data.util.Resource
import com.example.sahabss.data.util.makeNetworkRequest
import com.example.sahabss.util.UiStateResource
import javax.inject.Inject

private const val TAG = "TabRepositoryImpl"

class EmployeeRepositoryImpl @Inject constructor(
    private val apiService: EmployeeApiService
) : EmployeeRepository {

    override suspend fun getEmployees(): UiStateResource<List<Employee>> {
        return when (val response = makeNetworkRequest { apiService.getEmployees() }){
            is Resource.Success -> {
                UiStateResource.Success(EmployeeMapper.fromEmpoyeesDTO((response.data)))
            }
            is Resource.Failure -> {
                UiStateResource.Failure(response.error)
            }
        }
    }
    override suspend fun getEmployee(id: String): UiStateResource<Employee> {
        return when (val response = makeNetworkRequest { apiService.getEmployee(id) }){
            is Resource.Success -> {
                UiStateResource.Success(EmployeeMapper.fromEmpoyeeDTO(response.data))
            }
            is Resource.Failure -> {
                UiStateResource.Failure(response.error)
            }
        }
    }

    override suspend fun updateEmployee(id: String): UiStateResource<String> {
        return when (val response = makeNetworkRequest { apiService.updateEmployees(id) }){
            is Resource.Success -> {
                UiStateResource.Success(EmployeeMapper.fromupdateEmpoyeesDTO(response.data))
            }
            is Resource.Failure -> {
                UiStateResource.Failure(response.error)
            }
        }
    }

   override suspend fun deleteEmployee(id: String): UiStateResource<String> {
        return when (val response = makeNetworkRequest { apiService.deleteEmployees(id) }){
            is Resource.Success -> {
                UiStateResource.Success(EmployeeMapper.fromdeleteEmpoyeesDTO(response.data))
            }
            is Resource.Failure -> {
                UiStateResource.Failure(response.error)
            }
        }
    }
}