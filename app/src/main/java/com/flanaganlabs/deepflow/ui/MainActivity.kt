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

package com.flanaganlabs.deepflow.ui

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.flanaganlabs.deepflow.R
import com.flanaganlabs.deepflow.ui.adapter.ItemAdapter
import com.flanaganlabs.deepflow.data.local.Datasource
import com.flanaganlabs.deepflow.data.model.Affirmation
import com.flanaganlabs.deepflow.data.remote.Status
import com.flanaganlabs.deepflow.ui.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var myDataset: MutableList<Affirmation>
    private val mainActivityViewModel: MainActivityViewModel by viewModels()
    private lateinit var itemAdapter: ItemAdapter

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize data.
        //val myDataset = Datasource().loadAffirmations()

        init()
    }

    private fun init() {
        myDataset = mutableListOf()
        itemAdapter = ItemAdapter(this, myDataset)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.adapter = itemAdapter

        // Use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true)

        setApiResponseObserver()
    }

    private fun setApiResponseObserver() {
        mainActivityViewModel.getAllPosts().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        hideProgress()
                        resource.data?.let {
                                myDataset.addAll(it)
                                itemAdapter.notifyDataSetChanged()
                        }
                    }
                    Status.ERROR -> {
                        hideProgress()
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        showProgress()
                    }
                }
            }
        })
    }

    private fun showProgress() {
        clProgressbar.show()
    }

    private fun hideProgress() {
        clProgressbar.hide()
    }
}