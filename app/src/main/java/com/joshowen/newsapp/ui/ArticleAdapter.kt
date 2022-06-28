package com.joshowen.newsapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.joshowen.newsapp.databinding.ItemArticleBinding
import com.joshowen.newsrepository.room.models.Article

class ArticleAdapter(private val onArticleClicked: (article: Article) -> Unit) : PagingDataAdapter<Article, ArticleAdapter.ArticleViewHolder>(
    ArticleComparator
) {

    //region PagingDataAdapter
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = ItemArticleBinding.inflate(LayoutInflater.from(parent.context))
        return ArticleViewHolder(binding, onArticleClicked)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }
    //endregion

    //region ArticleViewHolder
    class ArticleViewHolder(
        binding: ItemArticleBinding,
        private val onArticleClicked: (article: Article) -> Unit
    ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        private val tvTitle = binding.tvTitle
        private var article: Article? = null

        fun bind(article: Article?) {
            this.article = article

            if (article != null) {
                tvTitle.text = article.title
            } else {
                //Todo: Placeholder logic here
            }
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            article?.let { article ->
                onArticleClicked(article)
            }
        }
    }

    //endregion

    //region DiffUtil.ItemCallback
    object ArticleComparator : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Article, newItem: Article) =
            oldItem == newItem
    }
    //endregion
}