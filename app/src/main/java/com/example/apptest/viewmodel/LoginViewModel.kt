package viewmodel

import androidx.lifecycle.ViewModel
import com.example.apptest.repository.UserRepository
import javax.inject.Inject

class LoginViewModel @Inject constructor(var userRepository: UserRepository) : ViewModel() {


    fun login() {
        userRepository.login()
    }

}