package yalexaner.konturtest.network.repos

import rx.Single
import yalexaner.konturtest.network.models.NetworkContact

interface MainScreenRepo {

    /**
     * Загружает [контакты][NetworkContact] из трёх источников и конкатенирует их в один лист
     */
    fun getContacts(): Single<List<NetworkContact>>
}