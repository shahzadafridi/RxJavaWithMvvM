package com.example.sahabss.data.remote.model.employee

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class Employee(
    val id: Int,
    val employeeAge: Int,
    val employeeName: String,
    val employeeSalary: Int,
    val profileImage: String
): Parcelable