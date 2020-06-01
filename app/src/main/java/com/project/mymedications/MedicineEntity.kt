package com.project.mymedications

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medicine_info_table")
data class MedicineEntity(
    @PrimaryKey(autoGenerate = true)
    var medId: Long = 0L ,

    @ColumnInfo(name = "medName")
var medName: String  = "" ,

@ColumnInfo(name = "medDescription")
var medDescription: String = "" ,

@ColumnInfo(name = "dosage1")
var dose1: String = "" ,

@ColumnInfo(name = "dosage2")
    var dose2: String = "" ,

@ColumnInfo(name = "dosage3")
    var dose3: String = "" ,

@ColumnInfo(name = "dosage4")
    var dose4: String = "" ,

@ColumnInfo(name = "time1")
var time1: String = "" ,

@ColumnInfo(name = "time2")
    var time2: String = "",

@ColumnInfo(name = "time3")
    var time3: String = "" ,

@ColumnInfo(name = "time4")
    var time4: String = ""

)