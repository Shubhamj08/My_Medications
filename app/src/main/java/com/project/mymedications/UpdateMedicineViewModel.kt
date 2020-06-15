package com.project.mymedications

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

class UpdateMedicineViewModel(
    private val medicineEntityKey: Long = 0L,
    dataSource: MedicineDatabaseDao
) : ViewModel() {

    val database = dataSource

    private val viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val updateMedicineFragment = UpdateMedicineFragment()

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
            updateMedicine.time1 = medicineDetails.time1
            updateMedicine.dose2 = medicineDetails.dose2
            updateMedicine.time2 = medicineDetails.time2
            updateMedicine.dose3 = medicineDetails.dose3
            updateMedicine.time3 = medicineDetails.time3
            updateMedicine.dose4 = medicineDetails.dose4
            updateMedicine.time4 = medicineDetails.time4
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