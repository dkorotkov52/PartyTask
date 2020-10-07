package ru.korotkov.party.view.ui.guestList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ru.korotkov.party.model.PartyInfo
import ru.korotkov.party.model.PartyInfoClient

class GuestListViewModel(application: Application) : AndroidViewModel(application) {
    val guestListLive = MutableLiveData<PartyInfo>()

    fun fetchGuestList() {
        PartyInfoClient.getInstance().getPartyInfo(getApplication<Application>().applicationContext)
            .apply {
                if (this != null) {
                    guestListLive.value = this
                }
            }
    }
}