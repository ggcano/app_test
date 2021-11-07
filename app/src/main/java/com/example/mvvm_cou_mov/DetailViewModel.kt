package com.example.mvvm_cou_mov

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm_cou_mov.dataDetail.LoompaDetail
import kotlinx.coroutines.launch
import retrofit2.Response

class DetailViewModel constructor(private val mainRepository: MainRepository) : ViewModel() {

    var responseDetailLoompa: MutableLiveData<Response<LoompaDetail>> = MutableLiveData()

    fun getLoompaDetail(id: Int){
        viewModelScope.launch {
            val response= mainRepository.getLoompaID(id)
            responseDetailLoompa.value = response
        }
    }
}