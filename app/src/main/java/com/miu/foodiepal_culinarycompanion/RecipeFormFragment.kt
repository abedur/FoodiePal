package com.miu.foodiepal_culinarycompanion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.miu.foodiepal_culinarycompanion.databinding.FragmentRecipeFormBinding
import com.miu.foodiepal_culinarycompanion.databinding.FragmentRecipesBinding


class RecipeFormFragment : DialogFragment() {
    interface RecipeFormListener {
        fun onRecipeAdded(recipe: Recipe)
    }

    private lateinit var listener: RecipeFormListener
    lateinit var recipeBinding : FragmentRecipeFormBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        recipeBinding = FragmentRecipeFormBinding.inflate(inflater, container,false)


        val editTitle: EditText = recipeBinding.editTitle //rootView.findViewById(R.id.editTitle)
        val editIngredients: EditText = recipeBinding.editIngredients
        val editInstructions: EditText = recipeBinding.editInstructions
        val btnAddRecipe: Button = recipeBinding.btnAddRecipe
        val btnCancel: Button = recipeBinding.btnCancel

        btnAddRecipe.setOnClickListener {
            val title = editTitle.text.toString()
            val ingredients = editIngredients.text.toString()
            val instructions = editInstructions.text.toString()

            if (title.isNotEmpty() && ingredients.isNotEmpty() && instructions.isNotEmpty()) {
                val newRecipe = Recipe(title, ingredients, instructions)
                listener.onRecipeAdded(newRecipe)
                dismiss()

            } else {
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_LONG).show()
            }
        }
        btnCancel.setOnClickListener {
            dismiss()
        }
        return recipeBinding.root
    }

    fun setListener(listener: RecipeFormListener) {
        this.listener = listener
    }


}