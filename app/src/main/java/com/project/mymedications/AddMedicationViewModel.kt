package com.project.mymedications

import android.app.AlarmManager
import android.app.Application
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.lifecycle.*
import kotlinx.coroutines.*
import java.lang.Math.random
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
            getRandomRequestId(),
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

        if(medicineDetails.hours1.isNotEmpty()) {

            var hr = medicineDetails.hours1.toInt()

            if( medicineDetails.mins1.isEmpty()) medicineDetails.mins1 = "00"
           // if(medicineDetails.meridiem1 == "pm" && (hr!=0 || hr!=12)) hr += 12

            val notificationTime = Calendar.getInstance().apply {
                timeInMillis = System.currentTimeMillis()
                set(Calendar.HOUR_OF_DAY, hr)
                set(Calendar.MINUTE, medicineDetails.mins1.toInt())
            }

            alarmManager.setInexactRepeating(
                AlarmManager.RTC_WAKEUP,
                notificationTime.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                notifyPendingIntent
            )
        }

        if(medicineDetails.hours2.isNotEmpty()) {

            var hr = medicineDetails.hours2.toInt()

            if( medicineDetails.mins2.isEmpty()) medicineDetails.mins2 = "00"
          //  if(medicineDetails.meridiem2 == "pm" && (hr!=0 || hr!=12)) hr += 12

            val notificationTime = Calendar.getInstance().apply {
                timeInMillis = System.currentTimeMillis()
                set(Calendar.HOUR_OF_DAY, hr)
                set(Calendar.MINUTE, medicineDetails.mins2.toInt())
            }

            alarmManager.setInexactRepeating(
                AlarmManager.RTC_WAKEUP,
                notificationTime.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                notifyPendingIntent
            )
        }

        if(medicineDetails.hours3.isNotEmpty()) {

            var hr = medicineDetails.hours3.toInt()

            if( medicineDetails.mins3.isEmpty()) medicineDetails.mins3 = "00"
          //  if(medicineDetails.meridiem3 == "pm" && (hr!=0 || hr!=12)) hr += 12

            val notificationTime = Calendar.getInstance().apply {
                timeInMillis = System.currentTimeMillis()
                set(Calendar.HOUR_OF_DAY, hr)
                set(Calendar.MINUTE, medicineDetails.mins3.toInt())
            }

            alarmManager.setInexactRepeating(
                AlarmManager.RTC_WAKEUP,
                notificationTime.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                notifyPendingIntent
            )
        }

        if(medicineDetails.hours4.isNotEmpty()) {

            var hr = medicineDetails.hours4.toInt()

            if( medicineDetails.mins4.isEmpty()) medicineDetails.mins4 = "00"
          //  if(medicineDetails.meridiem4 == "pm" && (hr!=0 || hr!=12)) hr += 12

            val notificationTime = Calendar.getInstance().apply {
                timeInMillis = System.currentTimeMillis()
                set(Calendar.HOUR_OF_DAY, hr)
                set(Calendar.MINUTE, medicineDetails.mins4.toInt())
            }

            alarmManager.setInexactRepeating(
                AlarmManager.RTC_WAKEUP,
                notificationTime.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                notifyPendingIntent
            )
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

