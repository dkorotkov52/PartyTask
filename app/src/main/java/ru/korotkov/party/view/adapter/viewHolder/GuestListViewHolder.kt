package ru.korotkov.party.view.adapter.viewHolder

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_guest_list_item.view.*
import ru.korotkov.party.BR
import ru.korotkov.party.model.Guest
import ru.korotkov.party.view.ui.guestList.GuestListViewModel
import ru.korotkov.party.view.utils.CircleTransform


class GuestListViewHolder constructor(
    private val dataBinding: ViewDataBinding,
    private val guestListViewModel: GuestListViewModel
) : RecyclerView.ViewHolder(dataBinding.root) {

    private val avatarImage = itemView.item_avatar
    fun setup(itemData: Guest) {
        dataBinding.setVariable(BR.itemData, itemData)
        dataBinding.executePendingBindings()

        Picasso.get().load(itemData.avatarURL).transform(CircleTransform()).into(avatarImage)
    }
}