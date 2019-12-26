package com.example.weatherapp.view.fragments.searchresult

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.data.City
import com.example.weatherapp.utils.string
import kotlinx.android.synthetic.main.item_search_result.view.*

class SearchResultAdapter (private var items: ArrayList<City>,
                           private val onItemClickListener: (City) -> Unit)
    : RecyclerView.Adapter<SearchResultAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return  ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_search_result, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = items[position]
        holder.onBindView(item)
        holder.itemView.setOnClickListener {
            onItemClickListener(item)
        }
    }

    override fun getItemCount() = items.size

    class ViewHolder(view: View): RecyclerView.ViewHolder(view)  {
        fun onBindView(item: City) {
            val location =  "${item.area}, ${item.region}, ${item.country}"
            itemView.textview_cityName.string(location)

        }
    }
}