package br.pucpr.appdev.prescript.ui.medicine

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.pucpr.appdev.prescript.R
import br.pucpr.appdev.prescript.repository.MedicineRepository
import kotlinx.coroutines.launch

class MedicineViewModel(
    private val repository: MedicineRepository
) : ViewModel() {

    private val _medicineStateEventData = MutableLiveData<MedicineState>()
    val medicineStateEventData: LiveData<MedicineState>
        get() = _medicineStateEventData

    private val _messageEventData = MutableLiveData<Int>()
    val messageEventData: LiveData<Int>
        get() = _messageEventData

    fun addOrUpdateMedicine(
        /*imageMedicine: String,*/
        nameMedicine: String,
        labName: String,
        activePrinciple: String,
        quantity: String,
        id: Long = 0)
    {
        if(id > 0)
        {
            updateMedicine(id, /*imageMedicine,*/ nameMedicine, labName, activePrinciple, quantity)
        }
        else
        {
            insertMedicine(/*imageMedicine,*/ nameMedicine, labName, activePrinciple, quantity)
        }

    }

    private fun updateMedicine(
        id: Long,
        //imageMedicine: String,
        nameMedicine: String,
        labName: String,
        activePrinciple: String,
        quantity: String
    ) = viewModelScope.launch {
        try {
            repository.updateMedicine(id, /*imageMedicine,*/ nameMedicine, labName, activePrinciple, quantity)

            _medicineStateEventData.value = MedicineState.Inserted
            _messageEventData.value = R.string.medicine_updated_successfully

        }
        catch (ex: Exception)
        {
            _messageEventData.value = R.string.medicine_error_to_insert
            Log.e(TAG, ex.toString())
        }
    }

    private fun insertMedicine(
        //imageMedicine: String,
        nameMedicine: String,
        labName: String,
        activePrinciple: String,
        quantity: String
    ) = viewModelScope.launch {
                        try {
                            val id = repository.insertMedicine(/*imageMedicine,*/ nameMedicine, labName, activePrinciple, quantity)
                            if(id > 0){
                                _medicineStateEventData.value = MedicineState.Inserted
                                _messageEventData.value = R.string.medicine_inserted_successfully
                            }
                        }
                        catch (ex: Exception)
                        {
                            _messageEventData.value = R.string.medicine_error_to_update
                            Log.e(TAG, ex.toString())
                        }
    }

    fun removeMedicine(id: Long) = viewModelScope.launch {
        try {
            if(id > 0) {
                repository.deleteMedicine(id)

                _medicineStateEventData.value = MedicineState.Deleted
                _messageEventData.value = R.string.medicine_deleted_successfully
            }

        }
        catch (ex: Exception)
        {
            _messageEventData.value = R.string.medicine_error_to_update
            Log.e(TAG, ex.toString())
        }
    }

    sealed class MedicineState {
        object Inserted: MedicineState()
        object Updated: MedicineState()
        object Deleted: MedicineState()
    }

    companion object {
        private val TAG = MedicineViewModel::class.java.simpleName
    }

}