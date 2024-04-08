package com.aldev.airportexplore.viewPager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.aldev.airportexplore.ui.NavaidsBindViewHolder
import com.aldev.airportexplore.ui.RecycleViewAdapter
import com.aldev.airportexplore.ui.Type
import com.aldev.airportexplore.databinding.FragmentNavaidsInfoBinding
import com.aldev.airportexplore.network.Navaids
import com.aldev.airportexplore.viewModel.AirportViewModel

class NavaidsInfoFragment : Fragment() {

    private val sharedViewModel: AirportViewModel by activityViewModels()

    private var _binding: FragmentNavaidsInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNavaidsInfoBinding.inflate(inflater,container,false)
        val recyclerView = binding.navaidsRecyclerView
       sharedViewModel.airport.observe(viewLifecycleOwner) {
           if(it.navaids != null){
               val adapter = RecycleViewAdapter<Navaids, NavaidsBindViewHolder>(
                   it.navaids, Type.NAVAIDS
               )
               recyclerView.adapter = adapter
           }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}