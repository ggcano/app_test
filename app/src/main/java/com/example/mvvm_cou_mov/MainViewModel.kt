package com.example.mvvm_cou_mov

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm_cou_mov.data.ListResultDTO
import com.example.mvvm_cou_mov.data.Loompa
import com.example.mvvm_cou_mov.data.ResultDTO
import kotlinx.coroutines.*
import retrofit2.Response


class MainViewModel constructor(private val mainRepository: MainRepository) : ViewModel() {

        val errorMessage = MutableLiveData<String>()
        val movieList = MutableLiveData<List<ResultDTO>>()
        var job: Job? = null
        var myCustomResponse:MutableLiveData<Response<ListResultDTO>> = MutableLiveData()

        val loading = MutableLiveData<Boolean>()

     /*   fun getAllMovies() {
            job = CoroutineScope(Dispatchers.IO).launch {
                val response = mainRepository.getAllMovies()
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        movieList.postValue(response.body())
                        loading.value = false
                    } else {
                        onError("Error : ${response.message()} ")
                    }
                }
            }

        }*/

 fun getCustomPost(page: Int) {
        viewModelScope.launch {
            val response = mainRepository.getCustomPost(page)
            myCustomResponse.value = response

        }
    }

        private fun onError(message: String) {
            errorMessage.value = message
            loading.value = false
        }

        override fun onCleared() {
            super.onCleared()
            job?.cancel()
        }

    }
