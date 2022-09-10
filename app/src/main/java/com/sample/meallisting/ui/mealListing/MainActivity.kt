package com.sample.meallisting.ui.mealListing

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.sample.meallisting.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MealListingViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initObserver()
        getData()
    }

    private fun initObserver() {

        lifecycleScope.launchWhenCreated {
            viewModel.mealLiveData.collectLatest {


                binding.pbLoading.visibility =
                    if(it.loading) View.VISIBLE
                    else View.GONE

                it.error?.let { errorMessage ->
                    if(errorMessage.isNotEmpty())
                        Toast.makeText(this@MainActivity, errorMessage, Toast.LENGTH_LONG)
                            .show()
                }

                it.mealList?.let { mealList ->
                    if(mealList.isNotEmpty()) {
                        val adapter = MealListingAdapter(mealList)
                        binding.rvMeal.adapter = adapter
                    }
                }

            }
        }
    }

    private fun getData() {
        viewModel.getMealByCategory()
    }
}