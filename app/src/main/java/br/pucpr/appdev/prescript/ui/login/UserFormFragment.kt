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
import br.pucpr.appdev.prescript.databinding.FragmentUserFormBinding
import br.pucpr.appdev.prescript.extension.hideKeyboard
import br.pucpr.appdev.prescript.extension.navigateWithAnimations
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth


/**
 * A simple [Fragment] subclass.
 * Use the [UserFormFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserFormFragment : Fragment(R.layout.fragment_user_form) {

    private var _binding: FragmentUserFormBinding? = null
    private val binding get() = _binding!!
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, group: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserFormBinding.inflate(inflater, group, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonAddUser.setOnClickListener {view ->
            hideKeyboard()
            addUser(view)
        }
    }

    private fun addUser(view: View)
    {
        val email = binding.inputEmailUser.text.toString()
        val senha = binding.inputPasswordUser.text.toString()

        if(validateEmail(email) || senha.isEmpty())
        {
            Snackbar.make(view, "Preencha todos os campos vazios! Ou verifique se o e-mail é válido!", Snackbar.LENGTH_SHORT).show()
        }
        else
        {
            auth.createUserWithEmailAndPassword(email,senha).addOnCompleteListener{ cadastro ->

                if(cadastro.isSuccessful)
                {
                    Snackbar.make(view, "Sucesso ao cadastrar o usuário!", Snackbar.LENGTH_SHORT).show()
                    binding.inputEmailUser.setText("")
                    binding.inputPasswordUser.setText("")
                }
                else
                {
                    if(cadastro.exception.toString().contains("The email address is already in use by another account"))
                    {
                        Snackbar.make(view,"Já existem um usuário com o mesmo e-mail!", Snackbar.LENGTH_SHORT).show()
                    }
                    else
                    {
                        Snackbar.make(view,"Falha ao cadastrar um usuário!", Snackbar.LENGTH_SHORT).show()

                    }

                }

            }
        }

    }

    private fun hideKeyboard()
    {
        val parentActivity = requireActivity()
        if(parentActivity is AppCompatActivity) {
            parentActivity.hideKeyboard()
        }
    }

    private fun validateEmail(edEmailL: String): Boolean {
        val emailPattern = Regex("[a-zA-Z\\d._-]+@[a-zA-Z]+\\.+[a-zA-Z]+")
        return when {
            edEmailL.trim().isEmpty() -> {
                true
            }
            !edEmailL.trim().matches(emailPattern) -> {
                true
            }
            else -> {
                false
            }
        }
    }


}