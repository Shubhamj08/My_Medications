package com.project.mymedications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat



private const val REQUEST_CODE = 0
private const val FLAGS = 0

fun NotificationManager.sendNotification(messageBody: String, applicationContext: Context){

    val builder = NotificationCompat.Builder(
        applicationContext,
        applicationContext.getString(R.string.notification_channel_ID)
    )
        .setSmallIcon(R.drawable.medicine_notification)
        .setContentTitle(applicationContext.getString(R.string.notification_title))
        .setContentText(messageBody)

    notify(getRandomRequestId(), builder.build())
}

fun getRandomRequestId(): Int{
    val start: Int = (0..100).random()
    val end: Int = (101..200).random()
    return (start..end).random()
}