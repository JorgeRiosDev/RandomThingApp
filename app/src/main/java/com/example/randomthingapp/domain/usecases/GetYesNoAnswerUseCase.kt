package com.example.randomthingapp.domain.usecases

import com.example.randomthingapp.data.model.YesNoResponse
import com.example.randomthingapp.data.repository.YesNoRepository

class GetYesNoAnswerUseCase(private val repository: YesNoRepository) {
    suspend operator fun invoke(): YesNoResponse {
        return repository.getYesNoAnswer()
    }
}