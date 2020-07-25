package com.project.mymedications

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medicine_info_table")
data class MedicineEntity(
    @PrimaryKey(autoGenerate = true)
    var medId: Long = 0L,

    @ColumnInfo(name = "medName")
    var medName: String = "",

    @ColumnInfo(name = "medDescription")
    var medDescription: String = "",

    @ColumnInfo(name = "dosage1")
    var dose1: String = "",

    @ColumnInfo(name = "dosage2")
    var dose2: String = "",

    @ColumnInfo(name = "dosage3")
    var dose3: String = "",

    @ColumnInfo(name = "dosage4")
    var dose4: String = "",

    @ColumnInfo(name = "time1")
    var time1: String = "",

    @ColumnInfo(name = "time2")
    var time2: String = "",

    @ColumnInfo(name = "time3")
    var time3: String = "",

    @ColumnInfo(name = "time4")
    var time4: String = "",

    @ColumnInfo(name = "hours1")
    var hours1: String = "",

    @ColumnInfo(name = "mins1")
    var mins1: String = "",

    @ColumnInfo(name = "meridiem1")
    var meridiem1: String = "",

    @ColumnInfo(name = "hours2")
    var hours2: String = "",

    @ColumnInfo(name = "mins2")
    var mins2: String = "",

    @ColumnInfo(name = "meridiem2")
    var meridiem2: String = "",

    @ColumnInfo(name = "hours3")
    var hours3: String = "",

    @ColumnInfo(name = "mins3")
    var mins3: String = "",

    @ColumnInfo(name = "meridiem3")
    var meridiem3: String = "",

    @ColumnInfo(name = "hours4")
    var hours4: String = "",

    @ColumnInfo(name = "mins4")
    var mins4: String = "",

    @ColumnInfo(name = "meridiem4")
    var meridiem4: String = ""
)