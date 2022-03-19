package com.example.sahabss.ui.employee.listing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sahabss.data.remote.model.employee.Employee
import com.example.sahabss.databinding.ItemEmployeeBinding
import com.example.sahabss.util.load

class EmployeeListingAdatper(
    val list: List<Employee>,
    onItemClicked: (Employee) -> Unit
) : RecyclerView.Adapter<EmployeeListingAdatper.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = ItemEmployeeBinding.inflate(LayoutInflater.from(parent.context))
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return list.size
    }


    inner class MyViewHolder(val binding: ItemEmployeeBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Employee){
            with(item){
                binding.image.load(profileImage)
                binding.name.setText(employeeName)
                binding.salary.setText(employeeSalary.toString())
                binding.age.setText(employeeAge.toString())
            }
        }
    }
}