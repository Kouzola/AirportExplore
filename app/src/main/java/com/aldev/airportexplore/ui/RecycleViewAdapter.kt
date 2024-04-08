package com.aldev.airportexplore.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aldev.airportexplore.databinding.ListViewNavaidsItemBinding
import com.aldev.airportexplore.databinding.ListViewRunwaysItemBinding

class RecycleViewAdapter<T,VM: RecyclerView.ViewHolder>(private val items: List<T>, private val type: Type):
    RecyclerView.Adapter<VM>() {

    @Suppress("UNCHECKED_CAST")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VM {
        if(type == Type.RUNWAY) {
            val binding = ListViewRunwaysItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            return RunwaysBindViewHolder(binding) as VM
        }
        val binding = ListViewNavaidsItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NavaidsBindViewHolder(binding) as VM
    }

    override fun onBindViewHolder(holder: VM, position: Int) {
        if(type == Type.RUNWAY){
            holder as RunwaysBindViewHolder
            holder.bind(items,position)
        }
        else if(type == Type.NAVAIDS){
            holder as NavaidsBindViewHolder
            holder.bind(items,position)
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

}

enum class Type{
    RUNWAY,NAVAIDS
}