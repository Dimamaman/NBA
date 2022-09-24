package com.example.fragments.playersFragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.NetworkResult
import com.example.apiService.ApiService
import com.example.apiService.RetrofitService
import com.example.data.PlayersList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PlayersViewModel: ViewModel() {
    private val apiService = RetrofitService.apiService

    private val _result: MutableLiveData<NetworkResult<PlayersList>> = MutableLiveData()
    val result: LiveData<NetworkResult<PlayersList>> = _result

    fun getResult() {
        viewModelScope.launch {
            try {
                val response = apiService.getAllPlayers()
                Log.d("HHH","${response.isSuccessful}")
                withContext(Dispatchers.Main) {

                    if (response.isSuccessful) {
                        response.body()?.let {
                            Log.d("MMM","$it")
                            _result.value = NetworkResult.Success(it)
                        }
                    }else {
                        _result.value = NetworkResult.Error(response.message())
                    }
                }
            }catch (e: java.lang.Exception) {
                _result.value = NetworkResult.Error(e.localizedMessage)
            }
        }
    }
}