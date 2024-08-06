package br.pucpr.appdev.prescript.ui.medicinelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.pucpr.appdev.prescript.data.db.entity.MedicineEntity
import br.pucpr.appdev.prescript.repository.MedicineRepository
import kotlinx.coroutines.launch

class MedicineListViewModel(
    private val repository: MedicineRepository
) : ViewModel() {

    private val _allMedicinesEvent = MutableLiveData<List<MedicineEntity>>()
    val allMedicinesEvent : LiveData<List<MedicineEntity>>
        get() = _allMedicinesEvent


    fun getMedicines() = viewModelScope.launch {
        _allMedicinesEvent.postValue(repository.getAllMedicines())
    }

}