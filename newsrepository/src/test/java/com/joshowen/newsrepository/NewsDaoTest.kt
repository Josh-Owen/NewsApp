package com.joshowen.newsrepository

import com.joshowen.newsrepository.base.BaseUnitTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

class NewsDaoTest : BaseUnitTest() {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun insertArticle() = runTest {
        assert(true)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun updateArticle() = runTest {
        assert(true)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun writeAndReadArticle() = runTest {
        assert(true)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun insertAndDeleteArticle() = runTest {
        assert(true)
    }
}