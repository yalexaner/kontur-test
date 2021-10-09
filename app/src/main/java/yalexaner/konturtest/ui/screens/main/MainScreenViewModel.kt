package yalexaner.konturtest.ui.screens.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import yalexaner.konturtest.base.Schedulers
import yalexaner.konturtest.network.models.NetworkContact
import yalexaner.konturtest.network.repos.MainScreenRepo
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    schedulers: Schedulers,
    mainScreenRepo: MainScreenRepo
) : ViewModel() {

    private val _contacts = MutableLiveData<List<NetworkContact>>()
    val contacts: LiveData<List<NetworkContact>> = _contacts

    init {
        mainScreenRepo.getContacts()
            .subscribeOn(schedulers.io)
            .observeOn(schedulers.main)
            .subscribe(
                { contacts -> _contacts.value = contacts },
                { error -> error.printStackTrace() }
            )
    }
}