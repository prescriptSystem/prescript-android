package br.pucpr.appdev.prescript.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import br.pucpr.appdev.prescript.R
import br.pucpr.appdev.prescript.databinding.FragmentLoginFormBinding
import br.pucpr.appdev.prescript.extension.hideKeyboard
import br.pucpr.appdev.prescript.extension.navigateWithAnimations
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth


/**
 * A simple [Fragment] subclass.
 * Use the [LoginFormFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFormFragment : Fragment(R.layout.fragment_login_form) {

    private var _binding: FragmentLoginFormBinding? = null
    private val binding get() = _binding!!
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, group: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginFormBinding.inflate(inflater, group, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        configureViewListeners()
        binding.btnLogin.setOnClickListener {view ->
            hideKeyboard()
            login(view)
        }


    }

    private fun login(view: View)
    {
        val email = binding.emailEditText.text.toString()
        val senha = binding.passwordEditText.text.toString()

        if(email.isEmpty() || senha.isEmpty())
        {
            Snackbar.make(view, "Preencha todos os campos vazios!", Snackbar.LENGTH_SHORT).show()
        }
        else
        {
            auth.signInWithEmailAndPassword(email,senha).addOnCompleteListener{ login ->

                if(login.isSuccessful)
                {
                    Snackbar.make(view, "Login efetuado com sucesso!", Snackbar.LENGTH_SHORT).show()
                    binding.emailEditText.setText("")
                    binding.passwordEditText.setText("")

                    findNavController().navigateWithAnimations(R.id.action_loginFormFragment_to_home2)
                }
                else
                {
                    Snackbar.make(view, "Não foi possível realizar o login!", Snackbar.LENGTH_SHORT).show()
                    binding.emailEditText.setText("")
                    binding.passwordEditText.setText("")
                }

            }
        }

    }


    private fun configureViewListeners()
    {
        binding.btnForgotPassword.setOnClickListener {
            findNavController().navigateWithAnimations(R.id.action_loginFormFragment_to_esqueceuSenhaFragment)
        }

        binding.btnSignUp.setOnClickListener {
            findNavController().navigateWithAnimations(R.id.action_loginFormFragment_to_userFormFragment)
        }
    }

    private fun hideKeyboard()
    {
        val parentActivity = requireActivity()
        if(parentActivity is AppCompatActivity) {
            parentActivity.hideKeyboard()
        }
    }


}