package ru.korotkov.party.view.ui.guestList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_guest_list.*
import ru.korotkov.party.databinding.FragmentGuestListBinding
import ru.korotkov.party.view.adapter.GuestListAdapter
import ru.korotkov.party.view.utils.CircleTransform

class GuestListFragment : Fragment() {

    private lateinit var viewDataBinding: FragmentGuestListBinding
    private lateinit var adapter: GuestListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentGuestListBinding.inflate(inflater, container, false).apply {
            viewmodel =
                ViewModelProvider(this@GuestListFragment).get(GuestListViewModel::class.java)
            lifecycleOwner = viewLifecycleOwner
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.viewmodel?.fetchGuestList()

        loadContent()
        setupAdapter()
        setupObservers()
    }

    private fun loadContent() {
        val partyAvatarImage = partyImage
        val inviterAvatarImage = inviterImage

        println(viewDataBinding.viewmodel?.guestListLive?.value?.bannerURL)
        println(viewDataBinding.viewmodel?.guestListLive?.value?.inviter?.avatarURL)
        Picasso.get().load(viewDataBinding.viewmodel?.guestListLive?.value?.bannerURL)
            .into(partyAvatarImage)
        Picasso.get().load(viewDataBinding.viewmodel?.guestListLive?.value?.inviter?.avatarURL)
            .transform(CircleTransform()).into(inviterAvatarImage)
    }

    private fun setupObservers() {
        viewDataBinding.viewmodel?.guestListLive?.observe(viewLifecycleOwner, Observer {
            adapter.updateGuestList(it.guests)
        })
    }

    private fun setupAdapter() {
        val viewModel = viewDataBinding.viewmodel
        if (viewModel != null) {
            adapter = GuestListAdapter(viewDataBinding.viewmodel!!)
            val layoutManager = LinearLayoutManager(activity)
            guest_list_rv.layoutManager = layoutManager
            guest_list_rv.adapter = adapter
        }
    }
}