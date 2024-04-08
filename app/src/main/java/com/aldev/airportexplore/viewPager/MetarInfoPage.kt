package com.aldev.airportexplore.viewPager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.aldev.airportexplore.databinding.FragmentMetarInfoPageBinding
import com.aldev.airportexplore.viewModel.AirportViewModel

class MetarInfoPage : Fragment() {

    private val sharedViewModel : AirportViewModel by activityViewModels()

    private var _binding: FragmentMetarInfoPageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMetarInfoPageBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply{
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}