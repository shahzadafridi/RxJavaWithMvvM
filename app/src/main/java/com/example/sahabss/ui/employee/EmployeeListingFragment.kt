package com.example.sahabss.ui.employee

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.sahabss.R
import com.example.sahabss.databinding.FragmentEmployeeListingBinding
import com.example.sahabss.util.UiStateResource
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class EmployeeListingFragment : Fragment() {

    private var _binding: FragmentEmployeeListingBinding? = null
    private val binding get() = _binding!!
    private val viewModel: EmployeeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getEmployees()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEmployeeListingBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observer()
    }

    private fun observer() {
        viewModel.employeesLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                UiStateResource.Loading -> {
                    Timber.d("${this.javaClass.canonicalName}: %s", "Loading")
                }
                is UiStateResource.Failure -> {
                    Timber.e("${this.javaClass.canonicalName}: %s", state.error.displayMessage)
                }
                is UiStateResource.Success -> {
                    Timber.d("${this.javaClass.canonicalName}: %s", state.data)
                    viewModel.deleteEmployeeById( state.data[0].id)
                    viewModel.updateEmployeeById(state.data[0].id)
                    findNavController().navigate(
                        EmployeeListingFragmentDirections.actionEmployeeListingFragmentToEmployeeDetailFragment(
                            state.data[0].id
                        )
                    )
                }
            }
        }
        viewModel.updateEmployeeLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                UiStateResource.Loading -> {
                    Timber.d("${this.javaClass.canonicalName}: %s", "Loading")
                }
                is UiStateResource.Failure -> {
                    Timber.e("${this.javaClass.canonicalName}: %s", state.error.displayMessage)
                }
                is UiStateResource.Success -> {
                    Timber.d("${this.javaClass.canonicalName}: %s", state.data)
                }
            }
        }

        viewModel.deleteEmployeeLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                UiStateResource.Loading -> {
                    Timber.d("${this.javaClass.canonicalName}: %s", "Loading")
                }
                is UiStateResource.Failure -> {
                    Timber.e("${this.javaClass.canonicalName}: %s", state.error.displayMessage)
                }
                is UiStateResource.Success -> {
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