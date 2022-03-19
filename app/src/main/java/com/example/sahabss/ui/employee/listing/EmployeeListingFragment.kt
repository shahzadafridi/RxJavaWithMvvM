package com.example.sahabss.ui.employee.listing

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.sahabss.data.remote.model.employee.Employee
import com.example.sahabss.databinding.FragmentEmployeeListingBinding
import com.example.sahabss.util.*
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class EmployeeListingFragment : Fragment() {

    private var _binding: FragmentEmployeeListingBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ListingEmployeeViewModel by viewModels()
    private val adatper: EmployeeListingAdatper by lazy {
        EmployeeListingAdatper(
            onItemClicked = { _, it ->
               onItemClicked(it)
            },
            onDeleteClicked = {pos, it ->
                onDeleteClicked(pos, it)
            }
        )
    }

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
        binding.recyclerView.apply {
            adapter = adatper
        }
        observer()
    }

    private fun onItemClicked(item: Employee){
        findNavController().navigate(
            EmployeeListingFragmentDirections.actionEmployeeListingFragmentToEmployeeDetailFragment(
                item.id
            )
        )
    }

    private fun onDeleteClicked(position: Int, item: Employee){
        binding.progresBar.show()
        viewModel.deleteEmployeeById(item.id)
        viewModel.deleteEmployeeLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                UiStateResource.Loading -> {
                    Timber.d("${this.javaClass.canonicalName}: %s", "Loading")
                }
                is UiStateResource.Failure -> {
                    binding.errorLay.apply {
                        errorLayout.show()
                        errorTv.setText(state.error.displayMessage)
                        errorRetryBtn.setOnSafeClickListener {
                            errorLayout.hide()
                            binding.progresBar.show()
                            viewModel.deleteEmployeeById(item.id)
                        }
                    }
                    Timber.e("${this.javaClass.canonicalName}: %s", state.error.displayMessage)
                }
                is UiStateResource.Success -> {
                    binding.progresBar.hide()
                    adatper.removeItem(position)
                    binding.root.showSnackBar(state.data)
                    Timber.d("${this.javaClass.canonicalName}: %s", state.data)
                }
            }
        }
    }

    private fun observer() {
        viewModel.employeesLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                UiStateResource.Loading -> {
                    Timber.d("${this.javaClass.canonicalName}: %s", "Loading")
                    binding.progresBar.show()
                }
                is UiStateResource.Failure -> {
                    binding.progresBar.hide()
                    binding.errorLay.apply {
                        errorLayout.show()
                        errorTv.setText(state.error.displayMessage)
                        errorRetryBtn.setOnSafeClickListener {
                            errorLayout.hide()
                            binding.progresBar.show()
                            viewModel.getEmployees()
                        }
                    }
                    Timber.e("${this.javaClass.canonicalName}: %s", state.error.displayMessage)
                }
                is UiStateResource.Success -> {
                    Timber.d("${this.javaClass.canonicalName}: %s", state.data)
                    binding.progresBar.hide()
                    adatper.updateList(state.data.toMutableList())
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}