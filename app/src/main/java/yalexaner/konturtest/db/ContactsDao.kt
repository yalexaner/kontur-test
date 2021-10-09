package yalexaner.konturtest.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactsDao {

    @Query("SELECT * FROM contacts")
    fun getAll(): Flow<List<CachedContact>>

    @Query("SELECT COUNT(id) FROM contacts")
    suspend fun getCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg contact: CachedContact)

    @Update
    suspend fun update(vararg contact: CachedContact)

    @Delete
    suspend fun delete(vararg contact: CachedContact)
}