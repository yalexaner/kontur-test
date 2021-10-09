package yalexaner.konturtest.network.repos

import rx.Single
import yalexaner.konturtest.network.client.HttpClient
import yalexaner.konturtest.network.models.NetworkContact
import javax.inject.Inject

class HttpMainScreenRepo @Inject constructor(
    private val client: HttpClient
) : MainScreenRepo {

    override fun getContacts(): Single<List<NetworkContact>> = client.getContactsFirst()
}