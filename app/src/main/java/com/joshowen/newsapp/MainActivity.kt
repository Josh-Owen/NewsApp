package com.joshowen.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel : MainActivityVM by viewModels()

    private val adapter : ArticleAdapter by lazy {
        ArticleAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.rvArticles)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        observe()


    }

    private fun observe() {
        lifecycleScope.launch {
            val items = viewModel.callAPI()
            adapter.submitList(items)
        }
    }
}