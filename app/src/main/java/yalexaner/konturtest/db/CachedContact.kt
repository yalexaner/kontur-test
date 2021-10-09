package yalexaner.konturtest.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import yalexaner.konturtest.base.models.Contact

@Entity(tableName = "contacts")
data class CachedContact(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "phone") val phone: String,
    @ColumnInfo(name = "height") val height: Float,
    @ColumnInfo(name = "biography") val biography: String,
    @ColumnInfo(name = "temperament") val temperament: Int,
    @ColumnInfo(name = "education_period_start") val educationPeriodStart: String,
    @ColumnInfo(name = "education_period_end") val educationPeriodEnd: String
) : Contact