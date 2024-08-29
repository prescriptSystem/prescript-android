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

        /** Ao clicar no botão "Enviar" o sistema chama duas funções. A primeira para esconder o teclado e a segunda para enviar um e-mail de recuperação de senha **/
        binding.buttonEsqueceuSenha.setOnClickListener {view ->
            hideKeyboard()
            sendEmailResetPassowrd(view)
        }
    }

    /** Função que inicia  recuperação de senha do usuário **/
    private fun sendEmailResetPassowrd(view: View)
    {
        /** O sistema se conecta ao Firebase Authentication para iniciar o processo de recuperação de senha **/
        auth.sendPasswordResetEmail(binding.inputEmailEsqueceuSenha.text.toString()).addOnCompleteListener {task ->
            /**
             * Se o e-mail informado for válido, mesmo que não haja um e-mail cadastrado na base de dados, o sistema mostra uma mensagem de sucesso
             *  E se o e-mail estiver cadastrado, o sistema enviar uma mensagem com o link para recuperação de da senha
             **/
            if(task.isSuccessful)
            {
                binding.inputEmailEsqueceuSenha.setText("")
                Snackbar.make(view, "Email de recuperação de senha enviado com sucesso!", Snackbar.LENGTH_SHORT).show()
            }
            else
            {
                /**
                 * Se o e-mail informado não for válido o sistema mostra uma mensagem de erro
                 *
                 **/
                Snackbar.make(view, "Não foi possível enviar o email de recuperação de senha!", Snackbar.LENGTH_SHORT).show()
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
}