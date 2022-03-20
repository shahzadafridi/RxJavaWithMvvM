package com.example.sahabss.ui.constraint

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.sahabss.R
import com.google.android.material.button.MaterialButton

class ConstraintLayoutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_constraint_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<MaterialButton>(R.id.next).setOnClickListener {
            findNavController().navigate(
                ConstraintLayoutFragmentDirections.actionConstraintLayoutFragmentToEmployeeListingFragment()
            )
        }
    }

}