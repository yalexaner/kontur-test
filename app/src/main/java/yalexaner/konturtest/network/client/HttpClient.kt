package yalexaner.konturtest.network.client

import retrofit2.http.GET
import rx.Single
import yalexaner.konturtest.network.models.Contact

interface HttpClient {

    @GET("generated-01.json")
    fun getContactsFirst(): Single<List<Contact>>

    @GET("generated-02.json")
    fun getContactsSecond(): Single<List<Contact>>

    @GET("generated-03.json")
    fun getContactsThird(): Single<List<Contact>>
}