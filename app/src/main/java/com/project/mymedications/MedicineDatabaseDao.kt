package com.project.mymedications

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface MedicineDatabaseDao {

    @Insert
    fun insert(medicine: MedicineEntity)

    @Update
    fun update(medicine: MedicineEntity)

    @Query("SELECT * from medicine_info_table WHERE medId = :key")
    fun get(key: Long): MedicineEntity?

    @Delete
    fun delete(medicine: MedicineEntity?)

    @Query("DELETE FROM medicine_info_table")
    fun clear()

    @Query("SELECT * FROM medicine_info_table ORDER BY medId DESC LIMIT 1")
    fun getRecentMedicine(): MedicineEntity?

    @Query("SELECT * FROM medicine_info_table ORDER BY medName")
    fun getAllMedicines(): LiveData<List<MedicineEntity>>

    @Query("SELECT * FROM medicine_info_table ORDER BY medName")
    fun getMedicines(): List<MedicineEntity>

    @Query("SELECT * from medicine_info_table WHERE medId = :key")
    fun getMedicineWithId(key: Long): LiveData<MedicineEntity>
}