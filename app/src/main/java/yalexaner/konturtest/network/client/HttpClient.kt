package yalexaner.konturtest.network.client

import retrofit2.http.GET
import rx.Single
import yalexaner.konturtest.network.models.NetworkContact

interface HttpClient {

    @GET("generated-01.json")
    fun getContactsFirst(): Single<List<NetworkContact>>

    @GET("generated-02.json")
    fun getContactsSecond(): Single<List<NetworkContact>>

    @GET("generated-03.json")
    fun getContactsThird(): Single<List<NetworkContact>>
}