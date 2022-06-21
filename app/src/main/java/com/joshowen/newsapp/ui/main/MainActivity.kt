package com.joshowen.newsapp.ui.main

import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.joshowen.newsapp.R
import com.joshowen.newsapp.base.BaseActivity
import com.joshowen.newsapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named


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
        return when(item.itemId) {
            R.id.actionSettings -> {
                navController.navigate(R.id.settingsFragment)
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    //endregion

    //region Navigation
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    //endregion

}