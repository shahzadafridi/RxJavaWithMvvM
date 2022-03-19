package com.example.sahabss.ui.employee.update

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sahabss.data.remote.repository.EmployeeRepository
import com.example.sahabss.util.UiStateResource
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateEmployeeViewModel @Inject constructor(
    private val repository: EmployeeRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _updateEmployeeLiveData = MutableLiveData<UiStateResource<String>>()
    val updateEmployeeLiveData: LiveData<UiStateResource<String>>
        get() = _updateEmployeeLiveData

    fun updateEmployeeById(id: Int, payload: Map<String,String?>,) {
        viewModelScope.launch {
            _updateEmployeeLiveData.value = UiStateResource.Loading
            repository.updateEmployee(compositeDisposable,id, payload){
                _updateEmployeeLiveData.postValue(it)
            }
        }
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}