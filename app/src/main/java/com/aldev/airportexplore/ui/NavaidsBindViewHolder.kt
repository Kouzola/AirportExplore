package com.aldev.airportexplore.ui

import androidx.recyclerview.widget.RecyclerView
import com.aldev.airportexplore.databinding.ListViewNavaidsItemBinding
import com.aldev.airportexplore.network.Navaids

class NavaidsBindViewHolder(private var binding: ListViewNavaidsItemBinding): RecyclerView.ViewHolder(binding.root) , ViewHolderInterfaceBind {

    @Suppress("UNCHECKED_CAST")
    override fun <T> bind(list: List<T>, position: Int) {
        val navaids = list as List<Navaids>
        binding.navaidsName.text = navaids[position].name
        binding.navaidsType.text = navaids[position].type
        binding.navaidsIdent.text = navaids[position].ident
        binding.navaidsFreq.text = navaids[position].freq
    }


}