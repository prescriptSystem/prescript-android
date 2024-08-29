package br.pucpr.appdev.prescript.ui.medicinelist

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import br.pucpr.appdev.prescript.R
import br.pucpr.appdev.prescript.databinding.FragmentMedicineListBinding
import br.pucpr.appdev.prescript.extension.navigateWithAnimations
import br.pucpr.appdev.prescript.model.Medicine
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

class MedicineListFragment : Fragment(R.layout.fragment_medicine_list) {

    private var _binding: FragmentMedicineListBinding? = null
    private val binding get() = _binding!!
    private val auth = FirebaseAuth.getInstance()
    private val db = Firebase.firestore

    /*private val viewModel: MedicineListViewModel by viewModels{
        object : ViewModelProvider.Factory {

            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val medicineDao: MedicineDao = AppDatabase.getInstance(requireContext()).medicineDao
                val repository: MedicineRepository = DatabaseDataSource(medicineDao)
                return MedicineListViewModel(repository) as T
            }
        }
    }*/

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
        var medicines: MutableList<Medicine> = mutableListOf()
        medicines.clear()
        db.collection("medicamentos")
            .get()
            .addOnSuccessListener { result ->
                var cont = 0
                for (document in result) {
                    val idMedicine = cont
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
                    cont++
                }

                Log.d(TAG, "DocumentSnapshot added with ID1: ${medicines}")
                val adapter  = MedicineListAdapter(medicines)
                binding.recyclerMedicine.adapter = adapter
                binding.recyclerMedicine.visibility = View.VISIBLE
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${exception}")
            }


    }

   override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser == null) {
            findNavController().navigateWithAnimations(R.id.action_medicineListFragment_to_loginFormFragment)
        }
    }

    /*private fun observerViewModelEvent() {
       viewModel.allMedicinesEvent.observe(viewLifecycleOwner) { allMedicines ->
            val medicineListAdapter = MedicineListAdapter(allMedicines).apply {
                onItemClick = { medicine ->
                    val directions = MedicineListFragmentDirections.actionMedicineListFragmentToMedicineFragment()
                    findNavController().navigateWithAnimations(directions)


                }

            }

                binding.recyclerMedicine.run {
                    setHasFixedSize(true)
                    adapter = medicineListAdapter
                }



        }




    }*/

    override fun onResume() {
        super.onResume()
       // viewModel.getMedicines()
    }

    private fun configureViewListeners()
    {
        binding.fabAddMedicine.setOnClickListener {
            findNavController().navigateWithAnimations(R.id.action_medicineListFragment_to_medicineFragment)
        }
    }
}