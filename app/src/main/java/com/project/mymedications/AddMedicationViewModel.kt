package com.project.mymedications

import android.app.Application
import android.widget.TextView
import androidx.lifecycle.*
import kotlinx.coroutines.*
import kotlin.math.absoluteValue

class AddMedicationViewModel(
    val database: MedicineDatabaseDao,
    application: Application
) : ViewModel() {

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
            if (medicine?.medId != 0L) {
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
            newMedicine.hours1 = medicineDetails.hours1
            newMedicine.mins1 = medicineDetails.mins1
            newMedicine.meridiem1 = medicineDetails.meridiem1
            newMedicine.dose2 = medicineDetails.dose2
            newMedicine.hours2 = medicineDetails.hours2
            newMedicine.mins2 = medicineDetails.mins2
            newMedicine.meridiem2 = medicineDetails.meridiem2
            newMedicine.dose3 = medicineDetails.dose3
            newMedicine.hours3 = medicineDetails.hours3
            newMedicine.mins3 = medicineDetails.mins3
            newMedicine.meridiem3 = medicineDetails.meridiem3
            newMedicine.dose4 = medicineDetails.dose4
            newMedicine.hours4 = medicineDetails.hours4
            newMedicine.mins4 = medicineDetails.mins4
            newMedicine.meridiem4 = medicineDetails.meridiem4
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

