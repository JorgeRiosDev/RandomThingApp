package com.example.randomthingapp.data.repository

import com.example.randomthingapp.data.model.YesNoResponse
import com.example.randomthingapp.data.remote.YesNoApi


class YesNoRepository(private val api: YesNoApi) {
    suspend fun getYesNoAnswer() = api.getAnswer()
}
