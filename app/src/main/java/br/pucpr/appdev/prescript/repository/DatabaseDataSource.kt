package br.pucpr.appdev.prescript.repository

import androidx.lifecycle.LiveData
import br.pucpr.appdev.prescript.data.db.dao.MedicineDao
import br.pucpr.appdev.prescript.data.db.entity.MedicineEntity

class DatabaseDataSource(
    private val medicineDao: MedicineDao
): MedicineRepository {
    override suspend fun insertMedicine(
       /*imageMedicine: String,*/
        nameMedicine: String,
        labName: String,
        activePrinciple: String,
        quantity: String
    ): Long {
        val medicine = MedicineEntity(
           /* imageMedicine = imageMedicine,*/
            nameMedicine = nameMedicine,
            labName = labName,
            activePrinciple = activePrinciple,
            quantity = quantity)
        return medicineDao.insert(medicine)
    }

    override suspend fun updateMedicine(
        id: Long,
        /*imageMedicine: String,*/
        nameMedicine: String,
        labName: String,
        activePrinciple: String,
        quantity: String
    ) {
        val medicine = MedicineEntity(
            id = id,
            /*imageMedicine = imageMedicine,*/
            nameMedicine = nameMedicine,
            labName = labName,
            activePrinciple = activePrinciple,
            quantity = quantity)
        medicineDao.update(medicine)
    }

    override suspend fun deleteMedicine(id: Long) {
        medicineDao.delete(id)
    }

    override suspend fun deleteAllMedicines() {
        medicineDao.deleteAll()
    }

    override suspend fun getAllMedicines(): List<MedicineEntity> {
        return medicineDao.getAll()
    }
}