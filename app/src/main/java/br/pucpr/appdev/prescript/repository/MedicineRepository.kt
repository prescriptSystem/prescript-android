package br.pucpr.appdev.prescript.repository

import androidx.lifecycle.LiveData
import br.pucpr.appdev.prescript.data.db.entity.MedicineEntity

interface MedicineRepository {

    suspend fun insertMedicine(nameMedicine: String, labName: String, activePrinciple: String, quantity: String): Long

    suspend fun updateMedicine(id: Long, /*imageMedicine: String,*/ nameMedicine: String, labName: String, activePrinciple: String, quantity: String)

    suspend fun deleteMedicine(id: Long)

    suspend fun deleteAllMedicines()

    suspend fun getAllMedicines(): List<MedicineEntity>
}