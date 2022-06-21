package com.joshowen.newsapp.ui

import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.joshowen.newsapp.R
import com.joshowen.newsapp.base.BaseActivity
import com.joshowen.newsapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val viewModel : MainActivityVM by viewModels()
    private lateinit var navController: NavController

    //region BaseActivity Overrides
    override fun inflateBinding(layoutInflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        super.initViews()
        navController = findNavController(R.id.fCv)
        NavigationUI.setupWithNavController(
            binding.bnvHomeNavigation,
            navController
        )
//        val navHostFragment =
//            supportFragmentManager.findFragmentById(R.id.fCv) as NavHostFragment
//        val navController = navHostFragment.navController
//        binding.bnvHomeNavigation.setupWithNavController(navController)
//        binding.
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
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    //endregion


}