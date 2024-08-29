package br.pucpr.appdev.prescript.ui.medicinelist

import android.content.ContentValues.TAG
import android.nfc.Tag
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.pucpr.appdev.prescript.data.db.entity.MedicineEntity
import br.pucpr.appdev.prescript.model.Medicine
import br.pucpr.appdev.prescript.repository.MedicineRepository
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.launch

class MedicineListViewModel(
    private val repository: MedicineRepository
) : ViewModel() {

    private val db = Firebase.firestore

    private val _allMedicinesEvent = MutableLiveData<List<Medicine>>()
    val allMedicinesEvent : LiveData<List<Medicine>>
        get() = _allMedicinesEvent


    fun getMedicines() = viewModelScope.launch {

        val medicines: MutableList<Medicine> = mutableListOf()

        db.collection("medicamentos")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val idMedicine = document.get("id")
                    val nameMedicine = document.get("nameMedicine")
                    val nameLab = document.get("nameLab")
                    val activePrinciple = document.get("activePrinciple")
                    val quantity = document.get("quantity")
                   var medicine = Medicine(idMedicine.toString(),
                       nameMedicine.toString(), nameLab.toString(),
                       activePrinciple.toString(), quantity.toString()
                   )
                    Log.d(TAG, "DocumentSnapshot added with ID: ${medicine}")

                   medicines.add(medicine)

                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${exception}")
            }

        _allMedicinesEvent.postValue(medicines)
    }

}