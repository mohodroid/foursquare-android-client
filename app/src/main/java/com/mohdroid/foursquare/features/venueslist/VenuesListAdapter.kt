package com.mohdroid.foursquare.features.venueslist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mohdroid.domain.entity.VenueEntity
import com.mohdroid.foursquare.R

class VenuesListAdapter(
    private val venues: ArrayList<VenueEntity>,
    private val ctx: Context,
    private val onItemClicked: (String) -> Unit
) : RecyclerView.Adapter<VenuesListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_venue, parent, false)
        return ViewHolder(
            view
        )
    }

    fun addRange(insertIndex: Int, venues: ArrayList<VenueEntity>) {
        this.venues.addAll(insertIndex, venues)
    }

    override fun getItemCount(): Int = venues.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(venues, position, ctx, onItemClicked)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title_text: TextView = view.findViewById(R.id.title_text)
        val address_text: TextView = view.findViewById(R.id.address_text)
        val rate_text: TextView = view.findViewById(R.id.rate_text)
        val ll_text: TextView = view.findViewById(R.id.ll_text)
        fun onBind(
            venues: List<VenueEntity>,
            position: Int,
            ctx: Context,
            onItemClicked: (String) -> Unit
        ) {
            title_text.text = venues[position].name
            address_text.text = venues[position].location.address
            rate_text.text = venues[position].rating.toString()
            ll_text.text = ctx.getString(
                R.string.ll,
                venues[position].location.lat,
                venues[position].location.lng
            )
            itemView.setOnClickListener {
                onItemClicked(venues[position].id)
            }
        }
    }
}