package com.joshowen.newsapp.ui.articles

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.joshowen.newsapp.databinding.ItemArticleBinding
import com.joshowen.newsrepository.retrofit.request.TopStoriesArticleResponse

class ArticleAdapter : ListAdapter<TopStoriesArticleResponse, ArticleAdapter.ArticleViewHolder>(
    DiffCallback()
) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = ItemArticleBinding.inflate(LayoutInflater.from(parent.context))
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    override fun getItemCount(): Int {
       return currentList.size
    }

    //region ItemCallback
    class DiffCallback : DiffUtil.ItemCallback<TopStoriesArticleResponse>() {

        override fun areItemsTheSame(oldItem: TopStoriesArticleResponse, newItem: TopStoriesArticleResponse): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: TopStoriesArticleResponse, newItem: TopStoriesArticleResponse): Boolean {
            return oldItem.title == newItem.title
        }
    }
    //endregion

    class ArticleViewHolder(binding : ItemArticleBinding) : RecyclerView.ViewHolder(binding.root) {

        private val tvTitle = binding.tvTitle

        init {

        }

        fun bind(article : TopStoriesArticleResponse) {
            tvTitle.text = article.title
        }
    }
}