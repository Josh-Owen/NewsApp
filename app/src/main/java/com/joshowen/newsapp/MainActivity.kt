package com.joshowen.newsapp

import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.joshowen.newsapp.base.BaseActivity
import com.joshowen.newsapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val viewModel : MainActivityVM by viewModels()

    private val adapter : ArticleAdapter by lazy {
        ArticleAdapter()
    }

    override fun inflateBinding(layoutInflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        super.initViews()

        binding.rvArticles.adapter = adapter
        binding.rvArticles.layoutManager = LinearLayoutManager(this)

    }

    override fun observeViewModel() {
        lifecycleScope.launch {
            binding.pbProgress.visibility = View.VISIBLE
            val items = viewModel.callAPI()
            adapter.submitList(items)
            binding.pbProgress.visibility = View.GONE
        }
    }
}