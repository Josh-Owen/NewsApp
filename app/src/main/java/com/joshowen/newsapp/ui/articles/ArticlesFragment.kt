package com.joshowen.newsapp.ui.articles

import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.joshowen.newsapp.ArticleAdapter
import com.joshowen.newsapp.base.BaseFragment
import com.joshowen.newsapp.databinding.FragmentArticlesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ArticlesFragment : BaseFragment<FragmentArticlesBinding>() {

    //region Variables

    private val viewModel : ArticlesFragmentVM by viewModels()

    private val adapter : ArticleAdapter by lazy {
        ArticleAdapter()
    }

    //endregion

    override fun inflateBinding(layoutInflater: LayoutInflater): FragmentArticlesBinding {
        return FragmentArticlesBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        super.initViews()
        binding.rvArticles.adapter = adapter
        binding.rvArticles.layoutManager = LinearLayoutManager(requireContext())
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