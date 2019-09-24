/*
 * Copyright 2019, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.example.android.marsrealestate.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.marsrealestate.network.MarsApi
import com.example.android.marsrealestate.network.MarsProperty
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * The [ViewModel] that is attached to the [OverviewFragment].
 */

enum class MarsApiStatus { LOADING, ERROR, DONE }

class OverviewViewModel : ViewModel() {

    private val _status = MutableLiveData<MarsApiStatus>()

    val status: LiveData<MarsApiStatus>
        get() = _status


    private val _properties = MutableLiveData<List<MarsProperty>>()

    val properties: LiveData<List<MarsProperty>>
        get() = _properties

    /**
     * Call getMarsRealEstateProperties() on init so we can display status immediately.
     */
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(
            viewModelJob + Dispatchers.Main )      //The Dispatchers.Main dispatcher uses the UI thread for its work.
                                // Because Retrofit does all its work on a background thread, there's no reason to use any other thread for the scope.
    init {
        getMarsRealEstateProperties()
    }

    /**
     * Sets the value of the status LiveData to the Mars API status.
     */
    private fun getMarsRealEstateProperties() {
       coroutineScope.launch {
           var getPropertiesDeferred = MarsApi.retrofitService.getProperties()
           try {
               _status.value = MarsApiStatus.LOADING
               var listResult = getPropertiesDeferred.await()
               //returns the result from the network call when the value is ready.
               //The await() method is non-blocking, so the Mars API service retrieves the data from the network without blocking the current thread
               // — which is important because we're in the scope of the UI thread.
               _status.value = MarsApiStatus.DONE
               _properties.value = listResult
           } catch (e: Exception) {
               _status.value = MarsApiStatus.ERROR
               _properties.value = ArrayList() //clearing the RecyclerView
           }
       }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
