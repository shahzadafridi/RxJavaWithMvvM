package com.example.sahabss.ui.employee.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sahabss.data.remote.model.employee.Employee
import com.example.sahabss.data.remote.repository.EmployeeRepository
import com.example.sahabss.util.UiStateResource
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailEmployeeViewModel @Inject constructor(
    private val repository: EmployeeRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _employeeLiveData = MutableLiveData<UiStateResource<Employee>>()
    val employeeLiveData: LiveData<UiStateResource<Employee>>
        get() = _employeeLiveData

    fun getEmployeeById(id: Int) {
        viewModelScope.launch {
            _employeeLiveData.value = UiStateResource.Loading
            repository.getEmployee(compositeDisposable,id){
                _employeeLiveData.postValue(it)
            }
        }
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}