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
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.database.SleepDatabase
import com.example.android.trackmysleepquality.database.SleepDatabaseDao
import com.example.android.trackmysleepquality.databinding.FragmentSleepTrackerBinding
import com.google.android.material.snackbar.Snackbar

/**
 * A fragment with buttons to record start and end times for sleep, which are saved in
 * a database. Cumulative data is displayed in a simple scrollable TextView.
 * (Because we have not learned about RecyclerView yet.)
 */
class SleepTrackerFragment : Fragment() {

    /**
     * Called when the Fragment is ready to display content to the screen.
     *
     * This function uses DataBindingUtil to inflate R.layout.fragment_sleep_quality.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentSleepTrackerBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_sleep_tracker, container, false)

        val application : Application = requireNotNull(this.activity).application

        // Create an instance of the ViewModel Factory.
        val dataSource : SleepDatabaseDao = SleepDatabase.getInstance(application).sleepDatabaseDao
        val viewModelFactory : SleepTrackerViewModelFactory = SleepTrackerViewModelFactory(dataSource,application)

        // Get a reference to the ViewModel associated with this fragment.
        val sleepTrackerViewModel : SleepTrackerViewModel =
                ViewModelProviders.of(
                        this, viewModelFactory).get(SleepTrackerViewModel::class.java)
        binding.sleepTrackerViewModel = sleepTrackerViewModel

        val adapter = SleepNightAdapter()
        binding.sleepList.adapter = adapter

        //supplying the fragment's viewLifecycleOwner as the lifecycle owner,
        // you can make sure this observer is only active when the RecyclerView is on the screen.
        sleepTrackerViewModel.nights.observe(viewLifecycleOwner, Observer {
            it?.let{
                //whenever you get a non-null value (for nights), assign the value to the adapter's data
                adapter.data = it
            }
        })

        sleepTrackerViewModel.navigateToSleepQuality.observe(this, Observer { night ->
            night?.let {
                // We need to get the navController from this, because button is not ready, and it
                // just has to be a view. For some reason, this only matters if we hit stop again
                // after using the back button, not if we hit stop and choose a quality.
                // Also, in the Navigation Editor, for Quality -> Tracker, check "Inclusive" for
                // popping the stack to get the correct behavior if we press stop multiple times
                // followed by back.

                this.findNavController().navigate(
                        SleepTrackerFragmentDirections
                                .actionSleepTrackerFragmentToSleepQualityFragment(night.nightId))
                // Reset state to make sure we only navigate once, even if the device
                // has a configuration change.
                sleepTrackerViewModel.doneNavigating()
            }
        })

        sleepTrackerViewModel.showSnackBarEvent.observe(this, Observer {
            if (it == true){
                Snackbar.make(
                        activity!!.findViewById(android.R.id.content),
                        getString(R.string.cleared_message),
                        Snackbar.LENGTH_SHORT // How long to display the message.
                ).show()
                sleepTrackerViewModel.doneShowingSnackbar()
            }
        })

        binding.startButton.setOnClickListener { sleepTrackerViewModel.onStartTracking() }
        binding.stopButton.setOnClickListener { sleepTrackerViewModel.onStopTracking() }
        binding.clearButton.setOnClickListener { sleepTrackerViewModel.onClear() }
        binding.setLifecycleOwner(this)
        return binding.root
    }
}
