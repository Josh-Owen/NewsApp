package com.joshowen.newsapp.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.viewbinding.ViewBinding
import com.joshowen.newsapp.utils.managers.SharedPreferenceManager
import javax.inject.Inject

abstract class BaseActivity<Binding : ViewBinding> : AppCompatActivity() {

    lateinit var binding: Binding

    @Inject
    lateinit var sharedPrefManager: SharedPreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        intent?.extras?.let {
            intentExtras(it)
        }
        super.onCreate(savedInstanceState)
        binding = inflateBinding(layoutInflater)
        applySettings()
        setContentView(binding.root)


        initViews()
    }


    override fun onResume() {
        super.onResume()
        observeViewModel()
    }

    abstract fun inflateBinding(layoutInflater: LayoutInflater): Binding

    abstract fun observeViewModel()

    open fun intentExtras(args: Bundle) {}

    open fun initViews() {}

    private fun applySettings() {

        if (sharedPrefManager.isDarkModeEnabled()) {
            delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_YES
        } else {
            delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_NO
        }
    }
}