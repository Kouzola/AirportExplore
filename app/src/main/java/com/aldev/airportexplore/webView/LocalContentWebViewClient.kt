package com.aldev.airportexplore.webView

import android.content.Intent
import android.net.Uri
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import androidx.webkit.WebViewAssetLoader
import androidx.webkit.WebViewClientCompat

open class LocalContentWebViewClient(private val assetLoader: WebViewAssetLoader): WebViewClientCompat() {

    override fun shouldInterceptRequest(
        view: WebView?,
        request: WebResourceRequest?
    ): WebResourceResponse? {
        return request?.let { assetLoader.shouldInterceptRequest(it.url) }
    }

    override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
        val url = request.url
        if(url != null && !url.equals("https://appassets.androidplatform.net/assets/HTML/WebMap.html")){
            view.context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url.toString())))
            return true
        }
        return false
    }

}