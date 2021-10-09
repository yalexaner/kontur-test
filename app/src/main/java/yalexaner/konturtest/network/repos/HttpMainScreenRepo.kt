package yalexaner.konturtest.network.repos

import rx.Single
import yalexaner.konturtest.network.client.HttpClient
import yalexaner.konturtest.network.models.Contact
import javax.inject.Inject

class HttpMainScreenRepo @Inject constructor(
    private val client: HttpClient
) : MainScreenRepo {

    override fun getContacts(): Single<List<Contact>> = client.getContactsFirst()
}