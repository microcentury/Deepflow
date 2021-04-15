/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.flanaganlabs.deepflow.data.local

import android.os.Build
import androidx.annotation.RequiresApi
import com.flanaganlabs.deepflow.R
import com.flanaganlabs.deepflow.data.model.AffirmationLocal

/**
 * [Datasource] generates a list of [AffirmationLocal]
 */
class Datasource() {

    @RequiresApi(Build.VERSION_CODES.N)
    fun loadAffirmations(): List<AffirmationLocal> {

        return listOf<AffirmationLocal>(
                AffirmationLocal(R.string.affirmationHead1, R.string.affirmationBody1, R.string.affirmationSource1, R.drawable.image1),
                AffirmationLocal(R.string.affirmationHead2, R.string.affirmationBody2, R.string.affirmationSource2, R.drawable.image2),
                AffirmationLocal(R.string.affirmationHead3, R.string.affirmationBody3, R.string.affirmationSource3, R.drawable.image3),
                AffirmationLocal(R.string.affirmationHead4, R.string.affirmationBody4, R.string.affirmationSource4, R.drawable.image4),
                AffirmationLocal(R.string.affirmationHead5, R.string.affirmationBody5, R.string.affirmationSource5, R.drawable.image5),
                AffirmationLocal(R.string.affirmationHead6, R.string.affirmationBody6, R.string.affirmationSource6, R.drawable.image6),
                AffirmationLocal(R.string.affirmationHead7, R.string.affirmationBody7, R.string.affirmationSource7, R.drawable.image7),
                AffirmationLocal(R.string.affirmationHead8, R.string.affirmationBody8, R.string.affirmationSource8, R.drawable.image8),
                AffirmationLocal(R.string.affirmationHead9, R.string.affirmationBody9, R.string.affirmationSource9, R.drawable.image9),
                AffirmationLocal(R.string.affirmationHead10, R.string.affirmationBody10, R.string.affirmationSource10, R.drawable.image10)
        )
    }
}

