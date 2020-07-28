package com.project.mymedications

import android.app.AlarmManager
import android.app.Application
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.lifecycle.*
import kotlinx.coroutines.*
import java.util.*

class AddMedicationViewModel(
    private val database: MedicineDatabaseDao,
    val app: Application
) : ViewModel() {

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var currentMedicine = MutableLiveData<MedicineEntity?>()

    private val alarmManager = app.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    private val notifyIntent = Intent(app, AlarmReceiver::class.java)

    private val notifyPendingIntent: PendingIntent

    init {
        initializeCurrentMedicine()

        notifyPendingIntent = PendingIntent.getBroadcast(
            app,
            0,
            notifyIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
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

        startTimer(medicineDetails)
    }

    private fun startTimer(medicineDetails: MedicineDetails) {

        val notificationTime = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, medicineDetails.hours1.toInt())
            set(Calendar.MINUTE, medicineDetails.mins1.toInt())
        }

        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            notificationTime.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            notifyPendingIntent
        )

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

