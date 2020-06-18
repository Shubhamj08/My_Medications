package com.project.mymedications

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*


class ViewMedicationViewModel(
    val database: MedicineDatabaseDao,
    val application: Application
) : ViewModel() {

    val medicines = database.getAllMedicines()

    var allMedicines = arrayListOf<MedicineEntity>()

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var currentMedicine = MutableLiveData<MedicineEntity?>()

    init {
        initializeCurrentMedicine()
        initializeAllMedicines()
    }

    private fun initializeAllMedicines(): List<MedicineEntity> {
        uiScope.launch {
            allMedicines = getMedicinesFromDatabase()
        }
        return allMedicines
    }

    private suspend fun getMedicinesFromDatabase(): ArrayList<MedicineEntity> {
        return withContext(Dispatchers.IO){
            database.getMedicines().forEach {
                allMedicines.add(it)
            }
            allMedicines
        }
    }

    private fun initializeCurrentMedicine() {
        uiScope.launch {
            currentMedicine.value = getCurrentMedicineFromDatabase()
        }
    }

    private suspend fun getCurrentMedicineFromDatabase(): MedicineEntity? {
        return withContext(Dispatchers.IO) {
            var medicine = database.getRecentMedicine()
            if (medicine?.medId != 0L) {
                medicine = null
            }
            medicine
        }
    }

    private val _navigateToMedicineDetail = MutableLiveData<Long>()
    val navigateToMedicineDetail
        get() = _navigateToMedicineDetail

    fun onMedicineEntityClicked(id: Long) {
        _navigateToMedicineDetail.value = id
    }

    fun onMedicineDetailNavigated() {
        _navigateToMedicineDetail.value = null
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


}