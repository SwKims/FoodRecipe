package com.ksw.recipeclone.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.ksw.recipeclone.R
import com.ksw.recipeclone.models.ExtendedIngredient
import com.ksw.recipeclone.util.Constants.Companion.BASE_IMAGE_URL
import com.ksw.recipeclone.util.RecipesDiffUtil
import kotlinx.android.synthetic.main.ingredients_row_layout.view.*

/**
 * Created by KSW on 2021-02-05
 */

class IngredientsAdapter : RecyclerView.Adapter<IngredientsAdapter.MyViewHolder>() {

    private var ingredientsList = emptyList<ExtendedIngredient>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.ingredients_row_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.iv_ingredients.load(BASE_IMAGE_URL + ingredientsList[position].image) {
            crossfade(600)
            error(R.drawable.ic_launcher_background)
        }
        holder.itemView.tv_ingredients_name.text = ingredientsList[position].name.capitalize()
        holder.itemView.tv_ingredients_amount.text = ingredientsList[position].amount.toString()
        holder.itemView.tv_ingredients_unit.text = ingredientsList[position].unit
        holder.itemView.tv_ingredients_desc.text = ingredientsList[position].consistency
        holder.itemView.tv_ingredients_desc2.text = ingredientsList[position].original
    }

    override fun getItemCount(): Int {
        return ingredientsList.size
    }

    fun setData(newIngredients: List<ExtendedIngredient>) {
        val ingredientsDiffUtil =
            RecipesDiffUtil(ingredientsList, newIngredients)
        val diffUtilResult = DiffUtil.calculateDiff(ingredientsDiffUtil)
        ingredientsList = newIngredients
        diffUtilResult.dispatchUpdatesTo(this)
    }


}