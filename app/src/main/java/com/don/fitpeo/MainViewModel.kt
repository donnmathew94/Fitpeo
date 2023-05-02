package com.don.fitpeo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.don.fitpeo.data.repository.MyRepository
import com.don.fitpeo.models.ResponseItem
import com.don.fitpeo.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MyRepository,
    application: Application
) : AndroidViewModel(application) {
    private val _response: MutableLiveData<NetworkResult<List<ResponseItem>>> = MutableLiveData()
    val response: LiveData<NetworkResult<List<ResponseItem>>> = _response

    fun fetchNetworkData() = viewModelScope.launch {
        repository.getData().collect { values ->
            _response.value = values
        }
    }
}