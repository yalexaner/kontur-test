package yalexaner.konturtest.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CachedContact::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun contactsDao(): ContactsDao
}