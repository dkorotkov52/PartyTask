package ru.korotkov.party.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.korotkov.party.databinding.ViewGuestListItemBinding
import ru.korotkov.party.model.Guest
import ru.korotkov.party.view.adapter.viewHolder.GuestListViewHolder
import ru.korotkov.party.view.ui.guestList.GuestListViewModel

class GuestListAdapter(private val guestListViewModel: GuestListViewModel) :
    RecyclerView.Adapter<GuestListViewHolder>() {
    private var guestList: List<Guest> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val dataBinding = ViewGuestListItemBinding.inflate(inflater, parent, false)
        return GuestListViewHolder(dataBinding, guestListViewModel)
    }

    override fun getItemCount() = guestList.size

    override fun onBindViewHolder(holder: GuestListViewHolder, position: Int) {
        holder.setup(guestList[position])
    }

    fun updateGuestList(guestList: List<Guest>) {
        this.guestList = guestList
        notifyDataSetChanged()
    }
}