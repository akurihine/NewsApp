package com.arkady.news.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.arkady.news.NewsViewModel

class ArticleFragment : Fragment() {
    var webView: WebView? = null
    var url: String? = null
        set(value) {
            field = value
            value?.let { webView?.loadUrl(it) }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = activity?.run {
            ViewModelProviders.of(this).get(NewsViewModel::class.java)
        }

        viewModel?.selectedArticle?.observe(this, Observer { article ->
            this@ArticleFragment.url = article?.url
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        webView = WebView(context).apply {
            layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
            loadUrl(url)
        }

        return webView
    }

    companion object {
        const val TAG = "ArticleFragment"

        fun newInstance(): ArticleFragment =
            ArticleFragment()

        fun show(fragmentManager: FragmentManager, containerId: Int) {
            val articleFragment = fragmentManager.findFragmentByTag(TAG) as? ArticleFragment

            if (articleFragment == null) {
                fragmentManager.beginTransaction()
                    .add(containerId,
                        newInstance()
                    )
                    .addToBackStack(TAG)
                    .commit()
            }
        }
    }
}