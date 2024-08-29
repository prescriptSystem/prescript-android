package br.pucpr.appdev.prescript.ui.home

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.fragment.findNavController
import br.pucpr.appdev.prescript.R
import br.pucpr.appdev.prescript.databinding.FragmentHomeBinding
import br.pucpr.appdev.prescript.databinding.FragmentMedicineListBinding
import br.pucpr.appdev.prescript.extension.navigateWithAnimations
import br.pucpr.appdev.prescript.model.Medicine
import br.pucpr.appdev.prescript.model.MenuItem
import br.pucpr.appdev.prescript.ui.medicinelist.MedicineListAdapter
import com.google.firebase.auth.FirebaseAuth



class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     *  Aqui o sistema está inflando um ListView com as opções do menu.
     *
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var itensMenu: MutableList<MenuItem> = mutableListOf()
        val iconMedicine: Drawable? = ResourcesCompat.getDrawable(resources, R.drawable.ic_medicine_black, null)
        val iconExams: Drawable? = ResourcesCompat.getDrawable(resources, R.drawable.ic_exam_black, null)
        val iconCostumers: Drawable? = ResourcesCompat.getDrawable(resources, R.drawable.ic_costumers, null)
        val itemMenuMedicine =  MenuItem( "Cadastro de Medicamentos", iconMedicine)
        val itemMenuExams =  MenuItem( "Cadastro de Exames", iconExams)
        val itemMenuCostumers =  MenuItem( "Cadastro de Clientes", iconCostumers)

        itensMenu.add(itemMenuMedicine)
        itensMenu.add(itemMenuExams)
        itensMenu.add(itemMenuCostumers)

        val adapter  = MenuAdapter(view.context, itensMenu)
        binding.lista.adapter = adapter
        //binding.lista.visibility = View.VISIBLE

        configureViewListeners()
    }

    /**
     *  Nesta função o sitema verifica se o usuário está logado. Se não estiver, o sistema redireciona para o login.
     *
     */
    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser == null) {
            findNavController().navigateWithAnimations(R.id.action_medicineListFragment_to_loginFormFragment)
        }
    }

    /**
     *  Nesta função o sitema verifica qual item da ListView foi clicado e redireciona para a tela correspondente.
     *
     */
    private fun configureViewListeners()
    {
        binding.lista.setOnItemClickListener { parent, view, position, id ->
            if (position.equals(0)) {
                findNavController().navigateWithAnimations(R.id.action_home2_to_medicineListFragment)
            }
        }
    }


}