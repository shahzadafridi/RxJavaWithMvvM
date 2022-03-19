package com.example.sahabss.ui.employee.update

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.sahabss.R
import com.example.sahabss.data.remote.model.employee.Employee
import com.example.sahabss.databinding.FragmentEmployeeUpdateBinding
import com.example.sahabss.util.*
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class EmployeeUpdateFragment : Fragment() {

    private var _binding: FragmentEmployeeUpdateBinding? = null
    private val binding get() = _binding!!
    private val viewModel: UpdateEmployeeViewModel by viewModels()
    private val args: EmployeeUpdateFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEmployeeUpdateBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateUI(args.employee)
        observer()
    }

    private fun updateUI(item: Employee) {
         binding.apply {
             name.setText(item.employeeName)
             salary.setText(item.employeeSalary.toString())
             age.setText(item.employeeAge.toString())
             update.setOnSafeClickListener {
                 updateEmployee()
             }
         }
    }

    private fun updateEmployee() {
         if (validation()){
             val map = mapOf(
                 "name" to binding.name.text.toString(),
                 "salary" to binding.salary.text.toString(),
                 "age" to binding.age.text.toString()
             )
             viewModel.updateEmployeeById(args.employee.id,map)
         }
    }

    private fun validation(): Boolean {
        var isValid = true
        if (binding.name.text.isNullOrEmpty()) {
            isValid = false
            binding.name.setError(getString(R.string.error_name))
        }
        if (binding.salary.text.isNullOrEmpty()) {
            isValid = false
            binding.salary.setError(getString(R.string.error_salary))
        }
        if (binding.age.text.isNullOrEmpty()) {
            isValid = false
            binding.age.setError(getString(R.string.error_age))
        }
        return isValid
    }

    private fun observer() {
        viewModel.updateEmployeeLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                UiStateResource.Loading -> {
                    binding.progresBar.show()
                    Timber.d("${this.javaClass.canonicalName}: %s", "Loading")
                }
                is UiStateResource.Failure -> {
                    binding.errorLay.apply {
                        errorLayout.show()
                        errorTv.setText(state.error.displayMessage)
                        errorRetryBtn.setOnSafeClickListener {
                            errorLayout.hide()
                            binding.progresBar.show()
                            updateEmployee()
                        }
                    }
                    Timber.e("${this.javaClass.canonicalName}: %s", state.error.displayMessage)
                }
                is UiStateResource.Success -> {
                    binding.progresBar.hide()
                    binding.root.showSnackBar(state.data)
                    Timber.d("${this.javaClass.canonicalName}: %s", state.data)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}