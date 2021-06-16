package com.example.apptest.viewmodel

import com.example.apptest.repository.MovementRepository
import com.example.apptest.repository.UserRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(val userRepository: UserRepository, val movementRepository: MovementRepository) : BaseViewModel() {


}