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

        /**
         * Nesta função, abaixo, o sistema verifica se os botões "Esqueceu a senha?" e "Cadastrar" foram clicados
         * e o sistema redireciona para a tela correspondete ao clique
         **/
        configureViewListeners()

        /** Ao clicar no botão "Login" o sistema chama duas funções. A primeira para esconder o teclado e a segunda para iniciar o login no sistema **/
        binding.btnLogin.setOnClickListener {view ->
            hideKeyboard()
            login(view)
        }


    }

    private fun login(view: View)
    {
        val email = binding.emailEditText.text.toString()
        val senha = binding.passwordEditText.text.toString()

        /** O sistema verifica se ao menos um dos campos está vazio. Se estiver uma mensagem é mostrada em tela para que todos os campos sejam preenchidos **/
        if(email.isEmpty() || senha.isEmpty())
        {
            Snackbar.make(view, "Preencha todos os campos vazios!", Snackbar.LENGTH_SHORT).show()
        }
        else
        {
            /** O sistema se conecta ao Firebase Authentication para iniciar o processo de login **/
            auth.signInWithEmailAndPassword(email,senha).addOnCompleteListener{ login ->

                /**
                 * Se o e-mail e a senha estiverem corretos o login é efetuado, uma mensagem de texto aparece em tela, os campos são limpos e
                 * e o sistema redireciona o usuário para a tela de menu
                 * **/
                if(login.isSuccessful)
                {
                    Snackbar.make(view, "Login efetuado com sucesso!", Snackbar.LENGTH_SHORT).show()
                    binding.emailEditText.setText("")
                    binding.passwordEditText.setText("")

                    findNavController().navigateWithAnimations(R.id.action_loginFormFragment_to_home2)
                }
                else
                {
                    /**
                     * Se o e-mail e a senha não estiverem corretos o login não é efetuado, uma mensagem de texto aparece em tela e os campos são limpos
                     * **/
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