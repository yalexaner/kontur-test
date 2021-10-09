package yalexaner.konturtest.network.repos

import rx.Single
import yalexaner.konturtest.network.models.Contact

interface MainScreenRepo {

    /**
     * Загружает [контакты][Contact] из трёх источников и конкатенирует их в один лист
     */
    fun getContacts(): Single<List<Contact>>
}