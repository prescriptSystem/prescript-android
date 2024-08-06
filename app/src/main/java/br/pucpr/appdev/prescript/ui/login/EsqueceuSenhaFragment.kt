package br.pucpr.appdev.prescript.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import br.pucpr.appdev.prescript.R
import br.pucpr.appdev.prescript.databinding.FragmentEsqueceuSenhaBinding
import br.pucpr.appdev.prescript.databinding.FragmentLoginFormBinding
import br.pucpr.appdev.prescript.extension.hideKeyboard
import br.pucpr.appdev.prescript.extension.navigateWithAnimations
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth



class EsqueceuSenhaFragment : Fragment(R.layout.fragment_esqueceu_senha) {

    private var _binding: FragmentEsqueceuSenhaBinding? = null
    private val binding get() = _binding!!
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, group: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEsqueceuSenhaBinding.inflate(inflater, group, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureViewListeners()
        binding.buttonEsqueceuSenha.setOnClickListener {view ->
            hideKeyboard()
            sendEmailResetPassowrd(view)
        }
    }

    private fun sendEmailResetPassowrd(view: View)
    {
        auth.sendPasswordResetEmail(binding.inputEmailEsqueceuSenha.text.toString()).addOnCompleteListener {task ->
            if(task.isSuccessful)
            {
                binding.inputEmailEsqueceuSenha.setText("")
                Snackbar.make(view, "Email de recuperação de senha enviado com sucesso!", Snackbar.LENGTH_SHORT).show()
            }
            else
            {
                Snackbar.make(view, "Não foi possível enviar o email de recuperação de senha!", Snackbar.LENGTH_SHORT).show()
            }

        }
    }

    private fun configureViewListeners()
    {
        /*binding.btnSignUp.setOnClickListener {
            findNavController().navigateWithAnimations(R.id.action_loginFormFragment_to_userFormFragment)
        }*/
    }

    private fun hideKeyboard()
    {
        val parentActivity = requireActivity()
        if(parentActivity is AppCompatActivity) {
            parentActivity.hideKeyboard()
        }
    }
}