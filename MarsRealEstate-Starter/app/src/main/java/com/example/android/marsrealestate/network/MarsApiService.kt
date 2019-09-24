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

package com.example.android.marsrealestate.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

private const val BASE_URL = " https://android-kotlin-fun-mars-server.appspot.com/"

//Retrofit needs at least two things available to it to build a web services API:
// the base URI for the web service, and a converter factory.
// ScalarsConverter supports strings and other primitive types
// MoshiConverterFactory to work with Moshi

private val retrofit = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi))
                                                .baseUrl(BASE_URL)
                                                .build()

// define how Retrofit talks to the web server using HTTP requests
interface MarsApiService {

    //When the getProperties() method is invoked, Retrofit appends the endpoint 'realestate' to the base URL
    // , and creates a Call object.
    //That Call object is used to start the request.
    @GET("realestate")
    fun getProperties():
            Call<List<MarsProperty>>

}

//The Retrofit `create()` method creates the Retrofit service itself with the MarsApiService interface.
// Because this call is expensive, and the app only needs one Retrofit service instance,
//   you expose the service to the rest of the app using a public object called MarsApi,
//   and lazily initialize the Retrofit service there.
// Now that all the setup is done, each time your app calls MarsApi.retrofitService,
//   it will get a singleton Retrofit object that implements MarsApiService.
object MarsApi {
    val retrofitService : MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java) }
}