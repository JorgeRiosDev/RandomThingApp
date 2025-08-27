package com.example.randomthingapp.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomthingapp.data.model.YesNoResponse
import com.example.randomthingapp.data.repository.YesNoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class YesNoViewModel @Inject constructor(
    private val repository: YesNoRepository
) : ViewModel() {

    private val _answer = MutableLiveData<YesNoResponse?>()
    val answer: LiveData<YesNoResponse?> = _answer

    fun fetchAnswer() {
        viewModelScope.launch {
            try {
                val response = repository.getYesNoAnswer()
                _answer.postValue(response)
            } catch (e: Exception) {
                _answer.postValue(null)
            }
        }
    }
}




fun String.capitalizeFirstChar(): String {
    return this.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase() else it.toString()
    }
}
