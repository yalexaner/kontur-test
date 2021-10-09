package yalexaner.konturtest.ui.screens.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import yalexaner.konturtest.base.Schedulers
import yalexaner.konturtest.db.CachedContact
import yalexaner.konturtest.db.ContactsDao
import yalexaner.konturtest.network.models.NetworkContact
import yalexaner.konturtest.network.repos.MainScreenRepo
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    schedulers: Schedulers,
    mainScreenRepo: MainScreenRepo,
    private val contactsDao: ContactsDao
) : ViewModel() {

    val contacts: LiveData<List<CachedContact>> = contactsDao.getAll().asLiveData()

    init {
        mainScreenRepo.getContacts()
            .subscribeOn(schedulers.io)
            .observeOn(schedulers.main)
            .subscribe(
                { contacts -> cacheContacts(contacts) },
                { error -> error.printStackTrace() }
            )
    }

    private fun cacheContacts(contacts: List<NetworkContact>) {
        val cachedContacts = contacts.map { networkContact -> networkContact.toCachedContact() }

        viewModelScope.launch {
            contactsDao.insert(*cachedContacts.toTypedArray())
        }
    }

    private fun NetworkContact.toCachedContact(): CachedContact {
        return CachedContact(
            id = id,
            name = name,
            phone = phone,
            height = height,
            biography = biography,
            temperament = temperament,
            educationPeriodStart = educationPeriod.start,
            educationPeriodEnd = educationPeriod.end
        )
    }
}