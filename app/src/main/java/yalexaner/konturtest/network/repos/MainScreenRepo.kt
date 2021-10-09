package yalexaner.konturtest.network.repos

import rx.Observable
import yalexaner.konturtest.network.models.NetworkContact

interface MainScreenRepo {

    /**
     * Загружает [контакты][NetworkContact] из трёх источников и конкатенирует их в один лист
     */
    fun getContacts(): Observable<List<NetworkContact>>
}