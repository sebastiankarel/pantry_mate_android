package de.slapps.pantrymate.ui.viewmodels

import androidx.lifecycle.ViewModel
import de.slapps.pantrymate.data.PantryRepository
import de.slapps.pantrymate.data.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel(
    private val userRepository: UserRepository,
    private val pantryRepository: PantryRepository
) : ViewModel() {

    private val _username = MutableStateFlow("")
    val username = _username.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    fun onUsernameChange(username: String) {
        _username.value = username
    }

    fun onPasswordChange(password: String) {
        _password.value = password
    }

    fun onLoginClick() {
        // TODO: Implement login logic
    }

    fun onCreateAccountClick() {
        // TODO: Implement create account logic
    }
}