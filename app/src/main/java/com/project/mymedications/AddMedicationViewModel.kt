package com.project.mymedications

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.*

class AddMedicationViewModel(
    val database: MedicineDatabaseDao,
    application: Application): ViewModel(){

    val addMedication: AddMedicationFragment = AddMedicationFragment()

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

        private var currentMedicine = MutableLiveData<MedicineEntity?>()
        init {
            initializeCurrentMedicine()
        }

    private fun initializeCurrentMedicine() {
        uiScope.launch {
            currentMedicine.value = getCurrentMedicineFromDatabase()
        }
    }

    private suspend fun getCurrentMedicineFromDatabase(): MedicineEntity? {
        return withContext(Dispatchers.IO) {
            var medicine = database.getRecentMedicine()
            if(medicine?.medId != 0L){
                medicine = null
            }
            medicine
        }
    }

    fun onDonePressed(medicineDetails: MedicineDetails) {
        uiScope.launch {
                val newMedicine = MedicineEntity()
            newMedicine.medName = medicineDetails.medName
            newMedicine.medDescription = medicineDetails.medDesc
            newMedicine.dose1 = medicineDetails.dose1
            newMedicine.time1 = medicineDetails.time1
            newMedicine.dose2 = medicineDetails.dose2
            newMedicine.time2 = medicineDetails.time2
            newMedicine.dose3 = medicineDetails.dose3
            newMedicine.time3 = medicineDetails.time3
            newMedicine.dose4 = medicineDetails.dose4
            newMedicine.time4 = medicineDetails.time4
                insert(newMedicine)
                currentMedicine.value = getCurrentMedicineFromDatabase()
        }
    }

    private suspend fun insert(medicine: MedicineEntity) {
        withContext(Dispatchers.IO) {
            database.insert(medicine)

        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}

