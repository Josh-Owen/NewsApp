package com.joshowen.newsapp.ui.starred

import android.view.LayoutInflater
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.joshowen.newsapp.R
import com.joshowen.newsapp.base.BaseFragment
import com.joshowen.newsapp.databinding.FragmentStarredBinding
import com.joshowen.newsapp.ui.ArticleAdapter
import com.joshowen.newsrepository.room.models.Article
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StarredFragment : BaseFragment<FragmentStarredBinding>() {

    //region Variables

    private val viewModel : StarredFragmentVM by viewModels()

    private val articlesAdapter : ArticleAdapter by lazy {
        ArticleAdapter(::articleClicked)
    }

    //endregion


    override fun inflateBinding(layoutInflater: LayoutInflater): FragmentStarredBinding {
        return FragmentStarredBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        super.initViews()

        articlesAdapter.addLoadStateListener {

            binding.pbProgress.visibility = if (it.refresh is LoadState.Loading) View.VISIBLE else View.GONE

            binding.tvNoArticles.isVisible = it.refresh is LoadState.NotLoading && articlesAdapter.itemCount == 0

        }

        binding.rvArticles.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = articlesAdapter
        }
    }

    override fun observeViewModel() {

        lifecycleScope.launch {
            viewModel.getStarredArticles().collectLatest {
                articlesAdapter.submitData(it)
            }
        }

    }

    private fun articleClicked(article: Article) {
        val bundle = bundleOf(getString(R.string.arguments_selected_article) to article)
        findNavController().navigate(R.id.viewArticleFragment, bundle)
    }
}