package com.thejan.assessment.digikraft_assignment.bike

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thejan.assessment.digikraft_assignment.data.model.Features
import com.thejan.assessment.digikraft_assignment.databinding.LayoutStationItemBinding

class StationListAdapter(private val onItemActionClick: OnItemActionClick) :
    RecyclerView.Adapter<StationListAdapter.BikeListAdapterViewModel>() {

    private var features = ArrayList<Features>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BikeListAdapterViewModel {
        val binding: LayoutStationItemBinding =
            LayoutStationItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return BikeListAdapterViewModel(binding)
    }

    override fun onBindViewHolder(holder: BikeListAdapterViewModel, position: Int) {
        holder.bind(features[position])
    }

    override fun getItemCount(): Int = features.size

    fun setItems(items: ArrayList<Features>) {
        features = items
        notifyDataSetChanged()
    }

    inner class BikeListAdapterViewModel(private val itemBinding: LayoutStationItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: Features) {
            itemBinding.tvLabel.text = item.properties.label
            itemBinding.tvBikeCount.text = item.properties.bikes.toString()
            itemBinding.tvPlacesCount.text = item.properties.freeRacks.toString()
            itemBinding.tvDistance.text = "800m" //TODO:Distance should be calculated

            itemView.setOnClickListener {
                onItemActionClick.onItemClick(item)
            }
        }
    }

    interface OnItemActionClick {
        fun onItemClick(features: Features)
    }
}