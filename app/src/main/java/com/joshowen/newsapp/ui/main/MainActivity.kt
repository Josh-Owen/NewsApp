package com.joshowen.newsapp.ui.main

import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import com.joshowen.newsapp.R
import com.joshowen.newsapp.base.BaseActivity
import com.joshowen.newsapp.databinding.ActivityMainBinding
import com.joshowen.newsapp.utils.hideView
import com.joshowen.newsapp.utils.setVisible
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val viewModel : MainActivityVM by viewModels()

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController


    //region BaseActivity Overrides
    override fun inflateBinding(layoutInflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        super.initViews()
        navController = findNavController(R.id.fCv)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.settingsFragment -> binding.bnvHomeNavigation.hideView()
                R.id.viewArticleFragment -> binding.bnvHomeNavigation.hideView()
                else -> binding.bnvHomeNavigation.setVisible()
            }
        }
        appBarConfiguration = AppBarConfiguration(setOf(R.id.articlesFragment, R.id.starredFragment))
        NavigationUI.setupWithNavController(binding.bnvHomeNavigation, navController)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }


    override fun observeViewModel() {

    }
    //endregion

    //region Options Menu

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

    //endregion

    //region Navigation
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    //endregion

}