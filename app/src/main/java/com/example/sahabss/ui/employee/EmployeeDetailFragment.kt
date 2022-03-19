package com.example.sahabss.ui.employee

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.sahabss.R
import com.example.sahabss.databinding.FragmentEmployeeDetailBinding
import com.example.sahabss.util.UiStateResource
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

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_EmployeeDetailFragment_to_EmployeeListingFragment)
        }
        observer()
    }

    private fun observer() {

        viewModel.employeeLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                UiStateResource.Loading -> {
                    Timber.d("${this.javaClass.name}: %s", "Loading")
                }
                is UiStateResource.Failure -> {
                    Timber.e("${this.javaClass.name}: %s", state.error.displayMessage)
                }
                is UiStateResource.Success -> {
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