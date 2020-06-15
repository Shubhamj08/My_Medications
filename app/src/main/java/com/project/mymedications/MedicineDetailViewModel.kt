package com.project.mymedications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

class MedicineDetailViewModel(
    private val medicineEntityKey: Long = 0L,
    dataSource: MedicineDatabaseDao
) : ViewModel() {

    val database = dataSource

    val medicineDetail = MedicineDetailFragment()

    private val viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val medicine: LiveData<MedicineEntity>

    init {
        medicine = database.getMedicineWithId(medicineEntityKey)
    }

    fun onDeleteButtonPressed() {
        uiScope.launch {
            val deleteMedicine = medicine.value ?: return@launch
            delete(deleteMedicine)
            _showSnackbarEvent.value = true
        }
    }

    private suspend fun delete(deleteMedicine: MedicineEntity?) {
        withContext(Dispatchers.IO) {
            database.delete(deleteMedicine)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private var _showSnackbarEvent = MutableLiveData<Boolean>()
    val showSnackBarEvent: LiveData<Boolean>
        get() = _showSnackbarEvent

    fun doneShowingSnackbar() {
        _showSnackbarEvent.value = false
    }

}