package com.fmt.kotlin.eyepetizer.discover.observer

import android.app.Activity
import android.view.KeyEvent
import android.webkit.WebView
import android.widget.LinearLayout
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.fmt.kotlin.eyepetizer.discover.activity.NewsDetailActivity
import com.just.agentweb.AgentWeb
import com.just.agentweb.WebViewClient

class AgentWebObserver(private val container: LinearLayout, private val mActivity: Activity) :
    DefaultLifecycleObserver {

    lateinit var mAgentWeb: AgentWeb

    override fun onCreate(owner: LifecycleOwner) {
        mAgentWeb = AgentWeb.with(mActivity)
            .setAgentWebParent(container, LinearLayout.LayoutParams(-1, -1))
            .useDefaultIndicator()
            .setWebViewClient(mWebViewClient)
            .createAgentWeb()
            .ready()
            .go(mActivity.intent.getStringExtra(NewsDetailActivity.WEB_URL))
    }

    override fun onPause(owner: LifecycleOwner) {
        mAgentWeb.webLifeCycle.onPause()
    }

    override fun onResume(owner: LifecycleOwner) {
        mAgentWeb.webLifeCycle.onResume()
    }

    override fun onDestroy(owner: LifecycleOwner) {
        mAgentWeb.webLifeCycle.onDestroy()
    }

    fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean =
        mAgentWeb.handleKeyEvent(keyCode, event)

    private val mWebViewClient: WebViewClient = object : WebViewClient() {
        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            val js =
                "javascript:(function() {document.getElementsByClassName(\"share-bar-container\")[0].style.display=\'none\';" +
                        "document.getElementsByClassName(\"footer-container j-footer-container\")[0].style.display=\'none\';" +
                        "document.getElementsByClassName(\"kyt-promotion-bar-positioner\")[0].style.display=\'none\';" +
                        "})()"
            mAgentWeb.jsAccessEntrace.callJs(js)
        }
    }
}