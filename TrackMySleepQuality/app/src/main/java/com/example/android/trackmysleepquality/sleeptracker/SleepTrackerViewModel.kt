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
 */

package com.example.android.trackmysleepquality.sleeptracker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.android.trackmysleepquality.database.SleepDatabaseDao
import com.example.android.trackmysleepquality.database.SleepNight
import com.example.android.trackmysleepquality.formatNights
import kotlinx.coroutines.*

/**
 * ViewModel for SleepTrackerFragment.
 */
class SleepTrackerViewModel(
        val database: SleepDatabaseDao,
        application: Application) : AndroidViewModel(application) {

    private val _navigateToSleepQuality = MutableLiveData<SleepNight>()

    val navigateToSleepQuality: LiveData<SleepNight>
        get() = _navigateToSleepQuality

    private val _navigateToSleepDetail = MutableLiveData<Long>()
    val navigateToSleepDetail
        get() = _navigateToSleepDetail

    private var _showSnackbarEvent = MutableLiveData<Boolean>()

    val showSnackBarEvent: LiveData<Boolean>
        get() = _showSnackbarEvent

    private var viewModelJob : Job = Job()

    //Using Dispatchers.Main means that coroutines launched in the uiScope will run on the main thread.
    private var uiScope : CoroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    //It is a Room feature that every time the data in the database changes, the LiveData nights is updated to show the latest data.
    //You never need to explicitly set the LiveData or update it. Room updates the data to match the database.
    val nights : LiveData<List<SleepNight>> = database.getAllNights()

    val nightString = Transformations.map(nights){
        nights -> formatNights(nights,application.resources)
    }

    private var tonight = MutableLiveData<SleepNight>()
    init {
        initializeTonight()
    }


    private fun initializeTonight() {
        uiScope.launch{
            tonight.value = getTonightFromDatabase()
        }
    }

    private suspend fun getTonightFromDatabase(): SleepNight? {
        //Use the I/O dispatcher, because getting data from the database is an I/O operation and has nothing to do with the UI.
        return withContext(Dispatchers.IO){
            var night = database.getTonight()
            if (night?.endTimeMilli != night?.startTimeMilli) {
                night = null
            }
            night
        }
    }

    fun onStartTracking(){
        uiScope.launch {
            val newNight = SleepNight()
            insert(newNight)
            tonight.value = getTonightFromDatabase()
        }
    }

    private suspend fun insert(night: SleepNight) {
        withContext(Dispatchers.IO) {
            database.insert(night)
        }
    }

    fun onStopTracking(){
        uiScope.launch {
            //If the end time hasn't been set yet, set the endTimeMilli to the current system time
            // and call update() with the night data.
            val oldNight =  tonight.value ?: return@launch       //the return@label syntax specifies the function from which this statement returns, among several nested functions
            oldNight.endTimeMilli = System.currentTimeMillis()
            update(oldNight)
            _navigateToSleepQuality.value =  oldNight
        }
    }

    private suspend fun update(night: SleepNight){
        withContext(Dispatchers.IO){
            database.update(night)
        }
    }

    fun onClear(){
        uiScope.launch {
            clear()
            tonight.value = null
            _showSnackbarEvent.value = true
        }
    }

    private suspend fun clear(){
        withContext(Dispatchers.IO){
            database.clear()
        }
    }

    override fun onCleared() {
        super.onCleared()
        // cancel all coroutines upon destruction
        viewModelJob.cancel()
    }

    fun doneNavigating(){
        _navigateToSleepQuality.value = null
    }

    fun doneShowingSnackbar() {
        _showSnackbarEvent.value = false
    }

    val startButtonVisible = Transformations.map(tonight){
        it == null
    }

    val stopButtonVisible = Transformations.map(tonight){
        it != null
    }

    val clearButtonVisible = Transformations.map(nights){
        it?.isNotEmpty()
    }

    fun onSleepNightClicked(id: Long) {
        _navigateToSleepDetail.value = id
    }

    fun onSleepDetailNavigated() {
        _navigateToSleepDetail.value = null
    }
}

