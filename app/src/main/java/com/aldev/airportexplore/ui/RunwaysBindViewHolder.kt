package com.aldev.airportexplore.ui

import androidx.recyclerview.widget.RecyclerView
import com.aldev.airportexplore.databinding.ListViewRunwaysItemBinding
import com.aldev.airportexplore.network.Runway

class RunwaysBindViewHolder(private var binding: ListViewRunwaysItemBinding): RecyclerView.ViewHolder(binding.root) , ViewHolderInterfaceBind {

    @Suppress("UNCHECKED_CAST")
    override fun <T>bind(list: List<T>, position: Int) {
        val runways = list as List<Runway>
        val runwayNumber = binding.runwaysNumber
        val runwayInfoText = binding.runwaysInfo
        runwayNumber.text = String.format("%s - %s", runways[position].runwayNumber1,
            runways[position].runwayNumber2)
        val isLighted = when(runways[position].isLighted){
            "1" -> "lighted"
            else -> "not lighted"
        }
        runwayInfoText.text = String.format("%s ft %s", runways[position].length,isLighted)
    }

}