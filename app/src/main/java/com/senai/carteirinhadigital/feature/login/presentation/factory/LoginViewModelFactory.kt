package com.senai.carteirinhadigital.feature.login.presentation.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.senai.carteirinhadigital.feature.login.domain.repository.LoginRepository
import com.senai.carteirinhadigital.feature.login.presentation.LoginViewModel

class LoginViewModelFactory(private val repository: LoginRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(repository = repository) as T
        }
        throw IllegalArgumentException("ViewModel desconhecido: ${modelClass.name}")
    }
}