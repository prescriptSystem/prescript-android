package br.pucpr.appdev.prescript.ui.medicine



import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.pucpr.appdev.prescript.R
import br.pucpr.appdev.prescript.data.db.AppDatabase
import br.pucpr.appdev.prescript.data.db.dao.MedicineDao
import br.pucpr.appdev.prescript.databinding.FragmentMedicineBinding
import br.pucpr.appdev.prescript.extension.hideKeyboard
import br.pucpr.appdev.prescript.repository.DatabaseDataSource
import br.pucpr.appdev.prescript.repository.MedicineRepository
import com.google.android.material.snackbar.Snackbar



class MedicineFragment : Fragment(R.layout.fragment_medicine) {

    private var _binding: FragmentMedicineBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MedicineViewModel by viewModels {
        object : ViewModelProvider.Factory {

            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val medicineDao: MedicineDao = AppDatabase.getInstance(requireContext()).medicineDao
                val repository: MedicineRepository = DatabaseDataSource(medicineDao)
                return MedicineViewModel(repository) as T
            }
        }
    }

   /* var galleryUri: Uri? = null
    val galleryLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) {
       galleryUri = it
        try{
            val name = context?.packageName
            context?.grantUriPermission(name, it, Intent.FLAG_GRANT_READ_URI_PERMISSION)
            binding.ivImageMedicineSelected.setImageURI(galleryUri)
        }catch(e:Exception){
            e.printStackTrace()
        }

    }*/


    override fun onCreateView(inflater: LayoutInflater, group: ViewGroup?,
                           savedInstanceState: Bundle?): View {
         //super.onCreate(savedInstanceState)

         _binding = FragmentMedicineBinding.inflate(inflater, group, false)
         return binding.root
     }

    private val args: MedicineFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.medicine?.let { medicine ->
            binding.buttonAddMedicine.text = getString(R.string.medicine_button_update)
            binding.inputNameMedicine.setText(medicine.nameMedicine)
            binding.inputNameLab.setText(medicine.labName)
            binding.inputActivePrinciple.setText(medicine.activePrinciple)
            binding.inputQuantity.setText(medicine.quantity)

            binding.buttonDeleteMedicine.visibility = View.VISIBLE

        }
        observeEvents()
        setListeners()
    }



    private fun observeEvents() {
        viewModel.medicineStateEventData.observe(viewLifecycleOwner) { medicineState ->
            when (medicineState) {
                is MedicineViewModel.MedicineState.Inserted -> {
                    clearFields()
                    hideKeyboard()

                    findNavController().popBackStack()
                }
                is MedicineViewModel.MedicineState.Updated -> {
                    clearFields()
                    hideKeyboard()
                    findNavController().popBackStack()
                }
                is MedicineViewModel.MedicineState.Deleted -> {
                    clearFields()
                    hideKeyboard()
                    findNavController().popBackStack()
                }
            }
        }

        viewModel.messageEventData.observe(viewLifecycleOwner) { stringResId ->
            Snackbar.make(requireView(), stringResId, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun clearFields()
    {
        binding.inputNameMedicine.text?.clear()
        binding.inputNameLab.text?.clear()
        binding.inputActivePrinciple.text?.clear()
        binding.inputQuantity.text?.clear()
    }

    private fun hideKeyboard()
    {
        val parentActivity = requireActivity()
        if(parentActivity is AppCompatActivity) {
            parentActivity.hideKeyboard()
        }
    }

    private fun setListeners() {
        binding.buttonAddMedicine.setOnClickListener{
            //val imageMedicine = galleryUri.toString()
            val nameMedicine = binding.inputNameMedicine.text.toString()
            val nameLab = binding.inputNameLab.text.toString()
            val activePrinciple = binding.inputActivePrinciple.text.toString()
            val quantity = binding.inputQuantity.text.toString()

            viewModel.addOrUpdateMedicine(/*imageMedicine,*/ nameMedicine, nameLab, activePrinciple, quantity, args.medicine?.id ?: 0)
        }

        binding.buttonDeleteMedicine.setOnClickListener {
            viewModel.removeMedicine(args.medicine?.id ?: 0)
        }

        //binding.buttonSelectImage.setOnClickListener {
            //galleryLauncher.launch("image/*")
        //}

    }




}