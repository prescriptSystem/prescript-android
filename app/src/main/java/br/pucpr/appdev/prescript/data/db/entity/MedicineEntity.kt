package br.pucpr.appdev.prescript.data.db.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.versionedparcelable.VersionedParcelize
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "medicine")
data class MedicineEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    /*val imageMedicine: String,*/
    val nameMedicine: String,
    val labName: String,
    val activePrinciple: String,
    val quantity: String
) : Parcelable