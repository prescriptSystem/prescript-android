package br.pucpr.appdev.prescript.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.pucpr.appdev.prescript.data.db.entity.MedicineEntity

@Dao
interface MedicineDao {

    @Insert
    suspend fun insert(medicine: MedicineEntity): Long

    @Update
    suspend fun update(medicine: MedicineEntity)

    @Query("DELETE FROM medicine WHERE id = :id")
    suspend fun delete(id: Long)

    @Query("DELETE FROM medicine")
    suspend fun deleteAll()

    @Query("SELECT * FROM medicine")
    suspend fun getAll(): List<MedicineEntity>
}