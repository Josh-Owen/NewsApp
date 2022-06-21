package com.joshowen.newsapp.ui.starred

import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.joshowen.newsapp.ArticleAdapter
import com.joshowen.newsapp.base.BaseFragment
import com.joshowen.newsapp.databinding.FragmentStarredBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StarredFragment : BaseFragment<FragmentStarredBinding>() {

    //region Variables

    private val viewModel : StarredFragmentVM by viewModels()

    private val adapter : ArticleAdapter by lazy {
        ArticleAdapter()
    }

    //endregion


    override fun inflateBinding(layoutInflater: LayoutInflater): FragmentStarredBinding {
        return FragmentStarredBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        super.initViews()
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