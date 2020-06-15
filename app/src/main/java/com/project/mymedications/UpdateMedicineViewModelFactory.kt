package com.project.mymedications

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class UpdateMedicineViewModelFactory(
    private val medicineEntityKey: Long,
    private val dataSource: MedicineDatabaseDao) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UpdateMedicineViewModel::class.java)) {
            return UpdateMedicineViewModel(medicineEntityKey, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}