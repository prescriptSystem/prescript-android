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


        /** Ao clicar no botão "Salvar" o sistema chama duas funções. A primeira para esconder o teclado e a segunda para adicionar um novo usuário **/
        binding.buttonAddUser.setOnClickListener {view ->
            hideKeyboard()
            addUser(view)
        }
    }

    /** Função responsável por adicionar um novo usuário **/
    private fun addUser(view: View)
    {
        val email = binding.inputEmailUser.text.toString()
        val senha = binding.inputPasswordUser.text.toString()

        /** Ao recuperar o e-mail e a senha digitadas pelo usuário o sistema verifica se os campos não estão vazios e se o e-mail é válido **/
        if(validateEmail(email) || senha.isEmpty())
        {
            Snackbar.make(view, "Preencha todos os campos vazios! Ou verifique se o e-mail é válido!", Snackbar.LENGTH_SHORT).show()
        }
        else
        {
            /** Se os campos forem válidos, o sistema se conecta ao Firebase na tentativa de adicionar o usuário **/
            auth.createUserWithEmailAndPassword(email,senha).addOnCompleteListener{ cadastro ->
                /** Se não houver nenhum erro, o usuário é adicionado com sucesso, uma mensagem de sucesso aparece na tela e os campos são limpos **/
                if(cadastro.isSuccessful)
                {
                    Snackbar.make(view, "Sucesso ao cadastrar o usuário!", Snackbar.LENGTH_SHORT).show()
                    binding.inputEmailUser.setText("")
                    binding.inputPasswordUser.setText("")
                }
                else
                {
                    /** Se houver algum erro, o sistema verifica a mensagem de erro retornada pelo Firebase e se tiver o texto abaixo,
                     * o sistema exibe uma mensagem que o e-mail já existe na base de dados **/
                    if(cadastro.exception.toString().contains("The email address is already in use by another account"))
                    {
                        Snackbar.make(view,"Já existe um usuário com o mesmo e-mail!", Snackbar.LENGTH_SHORT).show()
                    }
                    else
                    {
                        /** Se a mensagem de erro retornada pelo Firebase for diferente da mensagem acima,
                         * o sistema exibe uma mensagem genérica **/
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