package yalexaner.konturtest.network.repos

import rx.Observable
import rx.Single
import yalexaner.konturtest.network.client.HttpClient
import yalexaner.konturtest.network.models.NetworkContact
import javax.inject.Inject

class HttpMainScreenRepo @Inject constructor(
    private val client: HttpClient
) : MainScreenRepo {

    override fun getContacts(): Observable<List<NetworkContact>> {
        val contactsFirst = client.getContactsFirst()
        val contactsSecond = client.getContactsSecond()
        val contactsThird = client.getContactsThird()

        return Single.merge(contactsFirst, contactsSecond, contactsThird)
    }
}