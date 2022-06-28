package com.joshowen.newsapp.ui.articles

import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.joshowen.newsapp.R
import com.joshowen.newsapp.base.BaseFragment
import com.joshowen.newsapp.databinding.FragmentArticlesBinding
import com.joshowen.newsapp.ui.ArticleAdapter
import com.joshowen.newsrepository.room.models.Article
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged

@AndroidEntryPoint
class ArticlesFragment : BaseFragment<FragmentArticlesBinding>() {

    //region Variables

    private val viewModel: ArticlesFragmentVM by viewModels()

    private val articlesAdapter: ArticleAdapter by lazy {
        ArticleAdapter(::articleClicked)
    }


    //endregion



    override fun inflateBinding(layoutInflater: LayoutInflater): FragmentArticlesBinding {
        return FragmentArticlesBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        super.initViews()

        articlesAdapter.addLoadStateListener {

            binding.pbProgress.visibility = if (it.refresh is LoadState.Loading) VISIBLE else GONE

            binding.tvNoArticles.isVisible = it.refresh is LoadState.NotLoading && articlesAdapter.itemCount == 0

            binding.btnRetry.isVisible = it.refresh is LoadState.Error

        }

        binding.btnRetry.setOnClickListener {
            articlesAdapter.refresh()
        }

        binding.rvArticles.apply {
            adapter = articlesAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun observeViewModel() {

        lifecycleScope.launchWhenCreated {
            viewModel.getPager()
                .distinctUntilChanged()
                .collectLatest {
                    articlesAdapter.submitData(it)
                }
        }
    }

    private fun articleClicked(article: Article) {
        val bundle = bundleOf(getString(R.string.arguments_selected_article) to article)
        findNavController().navigate(R.id.viewArticleFragment, bundle)
    }
}
