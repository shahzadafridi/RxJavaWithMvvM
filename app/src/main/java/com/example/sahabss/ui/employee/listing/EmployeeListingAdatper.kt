package com.example.sahabss.ui.employee.listing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sahabss.data.remote.model.employee.Employee
import com.example.sahabss.databinding.ItemEmployeeBinding
import com.example.sahabss.util.load
import com.example.sahabss.util.setOnSafeClickListener

class EmployeeListingAdatper(
    val onItemClicked: (Int,Employee) -> Unit,
    val onDeleteClicked: (Int,Employee) -> Unit
) : RecyclerView.Adapter<EmployeeListingAdatper.MyViewHolder>() {

    private var list: MutableList<Employee> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = ItemEmployeeBinding.inflate(LayoutInflater.from(parent.context))
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    fun updateList(list: MutableList<Employee>){
        this.list = list
        notifyDataSetChanged()
    }

    fun removeItem(position: Int){
        list.removeAt(position)
        notifyItemChanged(position)
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
                binding.delete.setOnSafeClickListener {
                    onDeleteClicked.invoke(adapterPosition,item)
                }
                binding.itemEmployee.setOnSafeClickListener {
                    onItemClicked.invoke(adapterPosition,item)
                }
            }
        }
    }
}