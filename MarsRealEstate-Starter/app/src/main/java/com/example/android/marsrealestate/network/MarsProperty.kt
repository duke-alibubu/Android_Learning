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

import com.squareup.moshi.Json

data class MarsProperty(

        //When Moshi parses the JSON, it matches the keys by name and fills the data objects with appropriate values.

        val id: String,

        //To use variable names in your data class that differ from the key names in the JSON response, use the @Json annotation.
        @Json(name = "img_src")
        val imgSrcUrl: String,

        val type: String,
        val price: Double
)
