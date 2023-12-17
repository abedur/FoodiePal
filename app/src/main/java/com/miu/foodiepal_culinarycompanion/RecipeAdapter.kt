package com.miu.foodiepal_culinarycompanion

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecipeAdapter(var rlist: ArrayList<Recipe>):
    RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecipeAdapter.RecipeViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.recipe_list, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeAdapter.RecipeViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.rlName).text = "Name: "+rlist[position].recipeName
        holder.itemView.findViewById<TextView>(R.id.rlIngedient).text = "Ingredient: " +rlist[position].recipeIngredient
        holder.itemView.findViewById<TextView>(R.id.rlInstruction).text ="Instruction: " + rlist[position].recipeInstruction
    }

    override fun getItemCount(): Int {
        return rlist.size
    }
    inner class RecipeViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
}