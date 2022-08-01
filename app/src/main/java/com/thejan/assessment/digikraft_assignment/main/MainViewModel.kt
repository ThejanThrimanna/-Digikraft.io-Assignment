package com.thejan.assessment.digikraft_assignment.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thejan.assessment.digikraft_assignment.bike.StationRepository
import com.thejan.assessment.digikraft_assignment.data.model.Features
import com.thejan.assessment.digikraft_assignment.data.remote.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val stationRepository: StationRepository) :
    ViewModel() {
    private val _error: MutableLiveData<Any> =
        MutableLiveData()
    val error: LiveData<Any> = _error

    private val _loadList: MutableLiveData<List<Features>> =
        MutableLiveData()
    val loadList: LiveData<List<Features>> = _loadList

    fun getServices() {
        viewModelScope.launch {
            stationRepository.fetchMapServices().collect { response ->
                when (response) {
                    is NetworkResult.Success -> {
                        _loadList.value = response.data?.features
                    }
                    is NetworkResult.Error -> {
                        _error.value = Any()
                    }
                }
            }
        }
    }
}