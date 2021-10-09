package yalexaner.konturtest.ui.screens.main

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import yalexaner.konturtest.R.string.snackbar_no_connection
import yalexaner.konturtest.R.string.snackbar_unknown_error
import yalexaner.konturtest.base.GetString
import yalexaner.konturtest.base.Schedulers
import yalexaner.konturtest.data.settings.MainScreenSettings
import yalexaner.konturtest.db.CachedContact
import yalexaner.konturtest.db.ContactsDao
import yalexaner.konturtest.network.models.NetworkContact
import yalexaner.konturtest.network.repos.MainScreenRepo
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val schedulers: Schedulers,
    private val mainScreenRepo: MainScreenRepo,
    private val contactsDao: ContactsDao,
    private val mainScreenSettings: MainScreenSettings,
    private val getString: GetString
) : ViewModel() {

    val contacts: LiveData<List<CachedContact>> = contactsDao.getAll().asLiveData()

    private val _requestErrorMessage = MutableLiveData<String>()
    val requestErrorMessage: LiveData<String> = _requestErrorMessage

    init {
        val currentTime = System.currentTimeMillis()
        val lastSavedTime = mainScreenSettings.lastCachedTimeMillis
        if (currentTime - lastSavedTime >= MINUTE) {
            receiveContacts()
        }
    }

    private fun receiveContacts() {
        mainScreenRepo.getContacts()
            .subscribeOn(schedulers.io)
            .observeOn(schedulers.main)
            .subscribe(
                { contacts -> cacheContacts(contacts) },
                { error -> handleRequestError(error) }
            )
    }

    private fun cacheContacts(contacts: List<NetworkContact>) {
        val cachedContacts = contacts.map { networkContact -> networkContact.toCachedContact() }

        viewModelScope.launch {
            contactsDao.insert(*cachedContacts.toTypedArray())
            mainScreenSettings.lastCachedTimeMillis = System.currentTimeMillis()
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

    private fun handleRequestError(error: Throwable) {
        _requestErrorMessage.value = when (error) {
            is UnknownHostException, is SocketTimeoutException -> getString(snackbar_no_connection)
            else -> getString(snackbar_unknown_error)
        }
    }

    companion object {
        private const val MINUTE = 60 * 1000L
    }
}