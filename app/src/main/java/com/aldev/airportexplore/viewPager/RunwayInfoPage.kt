package com.aldev.airportexplore.viewPager

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.activityViewModels
import androidx.webkit.WebViewAssetLoader
import androidx.webkit.WebViewAssetLoader.AssetsPathHandler
import com.aldev.airportexplore.ui.RecycleViewAdapter
import com.aldev.airportexplore.ui.RunwaysBindViewHolder
import com.aldev.airportexplore.ui.Type
import com.aldev.airportexplore.webView.LocalContentWebViewClient
import com.aldev.airportexplore.databinding.FragmentRunwayInfoPageBinding
import com.aldev.airportexplore.network.Runway
import com.aldev.airportexplore.viewModel.AirportViewModel


class RunwayInfoPage : Fragment() {

    private val sharedViewModel : AirportViewModel by activityViewModels()

    private var _binding: FragmentRunwayInfoPageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRunwayInfoPageBinding.inflate(inflater,container, false)
        val recyclerView = binding.runwaysRecyclerView
        sharedViewModel.airport.observe(viewLifecycleOwner) { it ->
            val adapter = sharedViewModel.airport.value?.let {
                RecycleViewAdapter<Runway, RunwaysBindViewHolder>(
                    it.runways,
                    Type.RUNWAY
                )
            }
            recyclerView.adapter = adapter
            setWebMap(it.latitude, it.longitude)
        }
        return binding.root
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapWebView = binding.mapWebview
        mapWebView.setOnTouchListener { mapView, event ->
            mapView?.parent?.requestDisallowInterceptTouchEvent(true)
            mapView?.onTouchEvent(event) ?: true
        }
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setWebMap(latitude: Float, longitude: Float){
        val assetLoader = WebViewAssetLoader.Builder()
            .addPathHandler("/assets/",AssetsPathHandler(requireContext()))
            .build()
        val mapWebView = binding.mapWebview
        mapWebView.webViewClient = object : LocalContentWebViewClient(assetLoader){
            override fun onPageFinished(view: WebView?, url: String?) {
                val jsCode = "getAirport($latitude,$longitude)"
                Log.d("WebViewAirport",jsCode)
                mapWebView.evaluateJavascript(jsCode,null)
            }
        }
        val webViewSettings = mapWebView.settings
        webViewSettings.allowFileAccess = false
        webViewSettings.allowContentAccess = false
        webViewSettings.javaScriptEnabled = true
        mapWebView.loadUrl("https://appassets.androidplatform.net/assets/HTML/WebMap.html")
    }

}