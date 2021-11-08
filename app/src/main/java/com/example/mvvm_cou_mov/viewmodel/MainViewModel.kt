package com.example.mvvm_cou_mov.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm_cou_mov.repository.MainRepository
import com.example.mvvm_cou_mov.dataMain.ListResultDTO
import kotlinx.coroutines.*
import retrofit2.Response


class MainViewModel constructor(private val mainRepository: MainRepository) : ViewModel() {

    var job: Job? = null
    var responseListMLD: MutableLiveData<Response<ListResultDTO>> = MutableLiveData()

    fun getCustomPost(page: Int) {
        viewModelScope.launch {
            val response = mainRepository.getResponseResult(page)
            responseListMLD.value = response
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}
