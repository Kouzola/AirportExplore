package com.aldev.airportexplore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.aldev.airportexplore.databinding.FragmentFindAirportBinding
import com.aldev.airportexplore.viewModel.AirportViewModel


class FindAirportFragment : Fragment() {

    private val sharedViewModel: AirportViewModel by activityViewModels()

    private var _binding: FragmentFindAirportBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFindAirportBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            findAirportFragment = this@FindAirportFragment
        }
        val icaoEditText = binding.icao
        val searchButton: AppCompatButton = binding.searchButton
        searchButton.setOnClickListener{
            val icao = icaoEditText.text.toString().trim()
            if(icao.isNotEmpty()){
                sharedViewModel.searchAirport(icao)
                sharedViewModel.searchMetar(String.format(context?.resources!!.getString(R.string.metarEndpoint),icao))
                findNavController().navigate(R.id.action_findAirportFragment_to_airportInfoFragment2)
            }
        }

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}