package com.project.mymedications

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

class UpdateMedicineViewModel(
    medicineEntityKey: Long = 0L,
    dataSource: MedicineDatabaseDao
) : ViewModel() {

    private val database = dataSource

    private val viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val medicine: LiveData<MedicineEntity>

    init {
        medicine = database.getMedicineWithId(medicineEntityKey)
    }

    fun onUpdateButtonPressed(medicineDetails: MedicineDetails) {
        uiScope.launch {
            val updateMedicine = medicine.value ?: return@launch
            updateMedicine.medName = medicineDetails.medName
            updateMedicine.medDescription = medicineDetails.medDesc
            updateMedicine.dose1 = medicineDetails.dose1
            updateMedicine.hours1 = medicineDetails.hours1
            updateMedicine.mins1 = medicineDetails.mins1
            updateMedicine.meridiem1 = medicineDetails.meridiem1
            updateMedicine.dose2 = medicineDetails.dose2
            updateMedicine.hours2 = medicineDetails.hours2
            updateMedicine.mins2 = medicineDetails.mins2
            updateMedicine.meridiem2 = medicineDetails.meridiem2
            updateMedicine.dose3 = medicineDetails.dose3
            updateMedicine.hours3 = medicineDetails.hours3
            updateMedicine.mins3 = medicineDetails.mins3
            updateMedicine.meridiem3 = medicineDetails.meridiem3
            updateMedicine.dose4 = medicineDetails.dose4
            updateMedicine.hours4 = medicineDetails.hours4
            updateMedicine.mins4 = medicineDetails.mins4
            updateMedicine.meridiem4 = medicineDetails.meridiem4
            update(updateMedicine)
        }
    }

    private suspend fun update(updateMedicine: MedicineEntity) {
        withContext(Dispatchers.IO) {
            database.update(updateMedicine)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}