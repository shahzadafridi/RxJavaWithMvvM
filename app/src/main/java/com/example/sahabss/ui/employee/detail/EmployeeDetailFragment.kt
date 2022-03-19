package com.example.sahabss.ui.employee.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.sahabss.R
import com.example.sahabss.data.remote.model.employee.Employee
import com.example.sahabss.databinding.FragmentEmployeeDetailBinding
import com.example.sahabss.ui.employee.EmployeeViewModel
import com.example.sahabss.util.*
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class EmployeeDetailFragment : Fragment() {

    private var _binding: FragmentEmployeeDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: EmployeeViewModel by viewModels()
    private val args: EmployeeDetailFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getEmployeeById(args.employeeId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEmployeeDetailBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observer()
    }

    private fun updateUI(data: Employee){
        binding.apply {
            image.load(data.profileImage)
            name.setText(data.employeeName)
            salary.setText(data.employeeSalary.toString())
            age.setText(data.employeeAge.toString())
        }
    }

    private fun observer() {

        viewModel.employeeLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                UiStateResource.Loading -> {
                    binding.viewsGroup.invisible()
                    binding.progresBar.show()
                    Timber.d("${this.javaClass.name}: %s", "Loading")
                }
                is UiStateResource.Failure -> {
                    binding.errorLay.apply {
                        errorLayout.show()
                        errorTv.setText(state.error.displayMessage)
                        errorRetryBtn.setOnSafeClickListener {
                            errorLayout.hide()
                            binding.progresBar.show()
                            viewModel.deleteEmployeeById(args.employeeId)
                        }
                    }
                    Timber.e("${this.javaClass.name}: %s", state.error.displayMessage)
                }
                is UiStateResource.Success -> {
                    binding.progresBar.hide()
                    updateUI(state.data)
                    binding.viewsGroup.show()
                    Timber.d("${this.javaClass.name}: %s", state.data)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}