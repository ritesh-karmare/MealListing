package com.sample.meallisting.ui.mealListing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sample.meallisting.databinding.ItemMealBinding
import com.sample.meallisting.domain.model.Meal

class MealListingAdapter(private val mealList: List<Meal>) :
    RecyclerView.Adapter<MealListingAdapter.MealViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemMealBinding = ItemMealBinding.inflate(inflater, parent, false)
        return MealViewHolder(binding)
    }

    override fun getItemCount() = mealList.size

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) =
        holder.bind(mealList[position])

    inner class MealViewHolder(private val binding: ItemMealBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Meal) = with(binding) {
            Glide.with(ivMeal.context)
                .setDefaultRequestOptions(RequestOptions().circleCrop())
                .load(item.mealThumb)
                .into(ivMeal)

            tvMealName.text = item.mealName
        }
    }
}