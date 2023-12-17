package com.miu.foodiepal_culinarycompanion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class RecipesFragment : Fragment(), RecipeFormFragment.RecipeFormListener {
    val recipes = ArrayList<Recipe>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecipeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        recipes.add(Recipe("Boiled Egg","Eggs, Black paper, salt","Boil eggs about 30 min, peel off, spread salt and black paper"))
        recipes.add(Recipe("Mashed poteto","Potetos, Mastered oil, chilli flex, salt onion","Boil potetos about 30 min, peel off, spread salt, chopped onion, chilli flex and mix it with mastered oil"))

        val rootView = inflater.inflate(R.layout.fragment_recipes, container, false)
        recyclerView = rootView.findViewById(R.id.rcvRecipe)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = RecipeAdapter(recipes)
        recyclerView.adapter = adapter


        val btnAddRecipe:FloatingActionButton = rootView.findViewById(R.id.fabRecipe)

        btnAddRecipe.setOnClickListener {
            val formFragment = RecipeFormFragment()
            formFragment.setListener(this)
            formFragment.show(parentFragmentManager, "RecipeFormFragment")
        }

        return rootView
    }

    override fun onRecipeAdded(recipe: Recipe) {
        // Handle the new recipe addition
        recipes.add(recipe)
        adapter.notifyDataSetChanged()
    }

}

