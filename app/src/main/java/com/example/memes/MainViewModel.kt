package com.example.memes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainViewModel: ViewModel() {

    private val _response = MutableLiveData<Memes>()
    private val _loading = MutableLiveData<Boolean>()
    private val _failed = MutableLiveData<String>()

    val response: LiveData<Memes> = _response
    val loading: LiveData<Boolean> = _loading
    val failed: LiveData<String> = _failed

    init {
        _loading.value = true
        getApiResponse()
    }

    private fun getApiResponse(){
        MemesApi.apiService.getPhotos().enqueue(object: retrofit2.Callback<Memes> {
            override fun onResponse(call: Call<Memes>, response: Response<Memes>) {
                _response.value = response.body()
                _loading.value = false
            }

            override fun onFailure(call: Call<Memes>, t: Throwable) {
                _loading.value = false
                _failed.value = t.localizedMessage
            }
        })
    }


}