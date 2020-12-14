package com.fmt.kotlin.eyepetizer.discover.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import com.fmt.kotlin.eyepetizer.common.ext.immersionStatusBar
import com.fmt.kotlin.eyepetizer.common.ext.setToolBar
import com.fmt.kotlin.eyepetizer.discover.R
import com.fmt.kotlin.eyepetizer.discover.observer.AgentWebObserver
import kotlinx.android.synthetic.main.discover_activity_news_detail.*
import kotlinx.android.synthetic.main.discover_activity_topic_detail.mToolbar

class NewsDetailActivity : AppCompatActivity() {

    private val mAgentWebObserver by lazy { AgentWebObserver(container, this) }

    companion object {
        const val WEB_URL = "web_url"
        fun start(context: Context, url: String) {
            Intent(context, NewsDetailActivity::class.java).also {
                it.putExtra(WEB_URL, url)
                context.startActivity(it)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.discover_activity_news_detail)
        lifecycle.addObserver(mAgentWebObserver)
        immersionStatusBar(true, android.R.color.white, true, 0.2f)
        setToolBar(mToolbar)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return if (mAgentWebObserver.onKeyDown(keyCode, event)) {
            true
        } else {
            super.onKeyDown(keyCode, event)
        }
    }

}