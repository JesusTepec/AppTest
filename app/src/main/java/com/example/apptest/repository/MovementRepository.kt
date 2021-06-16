package com.example.apptest.repository

import com.example.apptest.rest.MovementsService
import javax.inject.Inject

class MovementRepository @Inject constructor(var movementsService: MovementsService) {
}