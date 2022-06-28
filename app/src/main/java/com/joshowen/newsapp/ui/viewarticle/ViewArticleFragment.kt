package com.joshowen.newsapp.ui.viewarticle

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.joshowen.newsapp.R
import com.joshowen.newsapp.base.BaseFragment
import com.joshowen.newsapp.databinding.FragmentViewArticleBinding
import com.joshowen.newsapp.ext.clicks
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ViewArticleFragment : BaseFragment<FragmentViewArticleBinding>() {

    //region Variables

    private val viewModel: ViewArticleFragmentVM by viewModels()

    private val args: ViewArticleFragmentArgs by navArgs()

    //endregion

    override fun inflateBinding(layoutInflater: LayoutInflater): FragmentViewArticleBinding {
        return FragmentViewArticleBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        super.initViews()
        setHasOptionsMenu(true)

        binding.btnOpenArticle.clicks().onEach {
            viewModel.clickedArticle()
        }.launchIn(lifecycleScope)
    }

    override fun observeViewModel() {

        lifecycleScope.launch {

            viewModel.selectedArticle(args.selectedArticle)

            repeatOnLifecycle(Lifecycle.State.STARTED) {

                launch {
                    viewModel.getArticleTitle().collectLatest {
                        binding.tvTitle.text = it
                    }
                }

                launch {
                    viewModel.getArticleContent().collectLatest {
                        binding.tvContentBody.text = it
                    }
                }

                launch {
                    viewModel.articleClicked().collectLatest {
                        args.selectedArticle.url?.let {
                            val intent = Intent(Intent.ACTION_VIEW).apply {
                                data = Uri.parse(it)
                            }
                            startActivity(intent)
                        }
                    }
                }

                launch {
                    viewModel.getFlow()
                        .distinctUntilChanged()
                        .collect {isStarred ->
                        Toast.makeText(
                            requireContext(),
                            if (isStarred) getString(R.string.toast_starred_article) else getString(
                                R.string.toast_un_starred_article
                            ),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                launch {
                    viewModel.getArticleDescription().collectLatest {
                        binding.tvDescriptionBody.text = it
                    }
                }

                launch {
                    viewModel.getArticleAuthor().collectLatest {
                        binding.tvAuthor.text = String.format(
                            getString(R.string.view_article_author_format),
                            it
                        )
                    }
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_article, menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.starArticle -> {
                lifecycle.coroutineScope.launch {
                    launch {
                        viewModel.starPressed()
                    }
                }
                false
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}