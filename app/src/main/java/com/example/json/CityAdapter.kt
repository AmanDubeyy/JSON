package com.example.json


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.json.databinding.ItemsBinding

class CityAdapter (
    var res : List<Cities>
): RecyclerView.Adapter<CityAdapter.CityViewHolder>() {

    inner class CityViewHolder(val binding: ItemsBinding) : RecyclerView.ViewHolder(binding.root)

    var onClickItem : ((Cities) -> Unit)? = null

    override fun onCreateViewHolder (parent: ViewGroup, viewType: Int): CityViewHolder {
        val binding = ItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CityViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return res.size
    }

    override fun onBindViewHolder (holder: CityViewHolder, position: Int) {

        val binding = holder.binding
        binding.textView.text = res[position].city
        binding.root.setOnClickListener {
            onClickItem?.invoke(res[position])
        }
    }
}