package br.pucpr.appdev.prescript.ui.medicinelist

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.camera.core.ImageCapture
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.navigation.fragment.findNavController
import br.pucpr.appdev.prescript.R
import br.pucpr.appdev.prescript.data.db.AppDatabase
import br.pucpr.appdev.prescript.data.db.dao.MedicineDao
import br.pucpr.appdev.prescript.data.db.entity.MedicineEntity
import br.pucpr.appdev.prescript.databinding.FragmentMedicineBinding
import br.pucpr.appdev.prescript.databinding.FragmentMedicineListBinding
import br.pucpr.appdev.prescript.extension.navigateWithAnimations
import br.pucpr.appdev.prescript.repository.DatabaseDataSource
import br.pucpr.appdev.prescript.repository.MedicineRepository
import br.pucpr.appdev.prescript.ui.medicine.MedicineViewModel
import com.google.firebase.auth.FirebaseAuth
import java.util.concurrent.ExecutorService

class MedicineListFragment : Fragment(R.layout.fragment_medicine_list) {

    private var _binding: FragmentMedicineListBinding? = null
    private val binding get() = _binding!!
    private val auth = FirebaseAuth.getInstance()

    private val viewModel: MedicineListViewModel by viewModels{
        object : ViewModelProvider.Factory {

            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val medicineDao: MedicineDao = AppDatabase.getInstance(requireContext()).medicineDao
                val repository: MedicineRepository = DatabaseDataSource(medicineDao)
                return MedicineListViewModel(repository) as T
            }
        }
    }

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }*/

   /* override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            findNavController().navigateWithAnimations(R.id.action_loginFormFragment_to_userFormFragment)
        }
    }*/

    override fun onCreateView(inflater: LayoutInflater, group: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        //super.onCreate(savedInstanceState)
        _binding = FragmentMedicineListBinding.inflate(inflater, group, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observerViewModelEvent()
        configureViewListeners()



    }



    private fun observerViewModelEvent() {
        viewModel.allMedicinesEvent.observe(viewLifecycleOwner) { allMedicines ->
            val medicineListAdapter = MedicineListAdapter(allMedicines).apply {
                onItemClick = { medicine ->
                    val directions = MedicineListFragmentDirections.actionMedicineListFragmentToMedicineFragment(medicine)
                    findNavController().navigateWithAnimations(directions)


                }

            }

                binding.recyclerMedicine.run {
                    setHasFixedSize(true)
                    adapter = medicineListAdapter
                }



        }




    }

    override fun onResume() {
        super.onResume()
        viewModel.getMedicines()
    }

    private fun configureViewListeners()
    {
        binding.fabAddMedicine.setOnClickListener {
            findNavController().navigateWithAnimations(R.id.action_medicineListFragment_to_medicineFragment)
        }
    }
}