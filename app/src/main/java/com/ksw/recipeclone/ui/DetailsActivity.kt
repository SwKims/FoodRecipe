package com.ksw.recipeclone.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.navArgs
import com.google.android.material.snackbar.Snackbar
import com.ksw.recipeclone.R
import com.ksw.recipeclone.adapters.PagerAdapter
import com.ksw.recipeclone.data.database.entities.FavoritesEntity
import com.ksw.recipeclone.ui.fragments.ingredients.IngredientsFragment
import com.ksw.recipeclone.ui.fragments.instructions.InstructionsFragment
import com.ksw.recipeclone.ui.fragments.overview.OverviewFragment
import com.ksw.recipeclone.util.Constants.Companion.RECIPE_RESULT_KEY
import com.ksw.recipeclone.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_details.*
import java.lang.Exception

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private val args by navArgs<DetailsActivityArgs>()
    private val mainViewModel: MainViewModel by viewModels()

    private var recipeSaved = false
    private var savedRecipeId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        setSupportActionBar(toolbar)
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // detail
        val fragments = ArrayList<Fragment>()
        fragments.add(OverviewFragment())
        fragments.add(IngredientsFragment())
        fragments.add(InstructionsFragment())

        val titles = ArrayList<String>()
        titles.add("Overview")
        titles.add("Ingredients")
        titles.add("Instructions")

        val resultBundle = Bundle()
        resultBundle.putParcelable(RECIPE_RESULT_KEY, args.result)

        val adapter = PagerAdapter(
            resultBundle,
            fragments,
            titles,
            supportFragmentManager
        )

        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        val menuItem = menu?.findItem(R.id.save_favorite)
        checkSavedRecipes(menuItem!!)
        return true
    }

    private fun checkSavedRecipes(menuItem: MenuItem) {
        mainViewModel.readFavoriteRecipes.observe(this) { favoritesEntity ->
            try {
                for (savedRecipe in favoritesEntity) {
                    if(savedRecipe.result.id == args.result.id) {
                        changeMenuItemColor(menuItem, R.color.yellow)
                        savedRecipeId = savedRecipe.id
                        recipeSaved = true
                    } else {
                        changeMenuItemColor(menuItem, R.color.white)
                    }
                }
            } catch (e: Exception) {
                Log.d("DetailsActivity", e.message.toString())
            }
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // detailview -> recipesview
        if (item.itemId == android.R.id.home) {
            finish()
        } else if (item.itemId == R.id.save_favorite && !recipeSaved) {
            saveToFavorites(item)
        } else if (item.itemId == R.id.save_favorite && recipeSaved) {
            removeFromFavorites(item)
        }

        return super.onOptionsItemSelected(item)

    }

    private fun saveToFavorites(item: MenuItem) {
        val favoriteEntity =
            FavoritesEntity(
                0, args.result
            )
        mainViewModel.insertFavoriteRecipes(favoriteEntity)
        changeMenuItemColor(item, R.color.yellow)
        showSnackBar("저장!")
        recipeSaved = true
    }

    private fun removeFromFavorites(item: MenuItem) {
        val favoritesEntity =
            FavoritesEntity(
                savedRecipeId,
                args.result
            )
        mainViewModel.deleteFavoriteRecipes(favoritesEntity)
        changeMenuItemColor(item, R.color.white)
        showSnackBar("삭제!")
        recipeSaved = false
    }


    private fun showSnackBar(message: String) {
        Snackbar.make(
            detailsLayout,
            message,
            Snackbar.LENGTH_SHORT
        ).setAction("Okay") { }
            .show()
    }

    private fun changeMenuItemColor(item: MenuItem, color: Int) {
        item.icon.setTint(ContextCompat.getColor(this, color))
    }

}