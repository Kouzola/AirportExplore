package com.aldev.airportexplore

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.aldev.airportexplore.databinding.FragmentAirportInfoBinding
import com.aldev.airportexplore.viewModel.AirportViewModel
import com.aldev.airportexplore.viewModel.ApiStatus
import com.aldev.airportexplore.viewPager.PageAdapter
import com.google.android.material.tabs.TabLayoutMediator

class AirportInfoFragment : Fragment() {


    private var _binding: FragmentAirportInfoBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: AirportViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAirportInfoBinding.inflate(inflater,container, false)
        sharedViewModel.status.observe(viewLifecycleOwner) {
            updateStatus(it)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar: Toolbar = binding.toolbar
        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout
        viewPager.adapter = PageAdapter(this)
        TabLayoutMediator(tabLayout, viewPager){tab, position ->
            when(position){
                0 -> tab.text = activity?.resources?.getText(R.string.runwaysInfoTab)
                1 -> tab.text = activity?.resources?.getText(R.string.metarInfoTab)
                2 -> tab.text = activity?.resources?.getText(R.string.navaidsInfoTab)
            }
        }.attach()
        toolbar.setNavigationOnClickListener{
            findNavController().navigate(R.id.action_airportInfoFragment2_to_findAirportFragment)
        }
        context?.let { sharedViewModel.checkInternetConnection(it) }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding.viewPager.adapter = null
        _binding = null
    }

    private fun updateStatus(status: ApiStatus){
        val loadingGif = binding.loadingGif
        val loadingText = binding.loadingText
        when(status){
            ApiStatus.LOADING -> {
                loadingGif.setImageResource(R.drawable.loading_gif)
                loadingGif.visibility = View.VISIBLE
                loadingText.visibility = View.VISIBLE
                hideLayout(true)

            }
            ApiStatus.ERROR -> {
                Log.d("AirportInfoFragment",sharedViewModel.isInternetAvailable.value.toString())
                if(!sharedViewModel.isInternetAvailable.value!!){
                    loadingGif.setImageResource(R.drawable.ic_connection_error)
                    loadingGif.visibility = View.VISIBLE
                    loadingText.visibility = View.INVISIBLE
                    Toast.makeText(context,"No Internet",Toast.LENGTH_SHORT).show()

                }else{
                    loadingGif.setImageResource(R.drawable.ic_error)
                    loadingGif.visibility = View.VISIBLE
                    loadingText.visibility = View.INVISIBLE
                    Toast.makeText(context,"Airport not found",Toast.LENGTH_SHORT).show()
                }
                hideLayout(true)
            }
            else -> {
                loadingText.visibility = View.INVISIBLE
                loadingGif.visibility = View.INVISIBLE
                hideLayout(false)
            }
        }
    }

    private fun hideLayout(hide: Boolean){
        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout
        if(hide){
            viewPager.visibility = View.INVISIBLE
            tabLayout.visibility = View.INVISIBLE
            return
        }
        viewPager.visibility = View.VISIBLE
        tabLayout.visibility = View.VISIBLE
    }

}