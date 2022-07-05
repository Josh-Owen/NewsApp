package com.joshowen.newsapp.base

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.joshowen.newsapp.ui.main.MainActivity
import org.junit.Before
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
open class BaseFragmentTest {

    @Before
    open fun setup() {
        ActivityScenario.launch(MainActivity::class.java)
    }

}