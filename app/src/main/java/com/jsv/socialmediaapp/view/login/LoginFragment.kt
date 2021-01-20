package com.jsv.socialmediaapp.view.login

import android.content.Context
import android.os.Bundle
import android.os.IBinder
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.jsv.socialmediaapp.AppExecutors
import com.jsv.socialmediaapp.databinding.FragmentLoginBinding
import com.jsv.socialmediaapp.di.Injectable
import com.jsv.socialmediaapp.service.ISessionManager
import com.jsv.socialmediaapp.util.autoCleared
import com.jsv.socialmediaapp.viewmodel.LoginFragmentViewModel
import javax.inject.Inject

class LoginFragment : Fragment(), Injectable, LogInHandler {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors


    var  binding by autoCleared<FragmentLoginBinding>()

    private val viewModel: LoginFragmentViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(LoginFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //super.onViewCreated(view, savedInstanceState)

        initLoginInput()

        isLogin()
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.handler = this

        viewModel.user.observe(viewLifecycleOwner, Observer { it ->
            it.data?.let {user->
              findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToNewsFeedFragment
                  (user.id,user.email,user.name,user.githubUsername,user.registeredAt,user.rating))
            }
        })
    }

    private fun isLogin() {
      viewModel.isLogin.observe(viewLifecycleOwner, Observer { user->
         findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToNewsFeedFragment
           (user.id,user.email,user.name,user.githubUsername,user.registeredAt,user.rating))
       })
    }

    private fun login(view: View) {
        dismissKeyboard(view.windowToken)
        viewModel.setLogin(binding.emailEdtLogin.text.toString(),
            binding.passwordEdtLogin.text.toString())
    }

    override fun onLogIn() {
        viewModel.setLogin(binding.emailEdtLogin.text.toString(),
            binding.passwordEdtLogin.text.toString())
    }

    private fun initLoginInput() {
        binding.passwordEdtLogin.setOnEditorActionListener { view: View, actionId: Int, _: KeyEvent? ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                login(view)
                true
            } else {
                false
            }
        }

        binding.emailEdtLogin.setOnEditorActionListener { view: View, actionId: Int, _: KeyEvent? ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                login(view)
                true
            } else {
                false
            }
        }

        binding.passwordEdtLogin.setOnKeyListener { view: View, keyCode: Int, event: KeyEvent ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                login(view)
                true
            } else {
                false
            }
        }

        binding.emailEdtLogin.setOnKeyListener { view: View, keyCode: Int, event: KeyEvent ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                login(view)
                true
            } else {
                false
            }
        }
    }

    private fun dismissKeyboard(windowToken: IBinder) {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(windowToken, 0)
    }
}

