package yalexaner.konturtest.data.settings

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MainScreenSettings @Inject constructor(
    @ApplicationContext context: Context
) {

    private val preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

    var lastCachedTimeMillis: Long
        get() = preferences.getLong(LAST_CACHED_TIME, 0)
        set(value) = preferences.edit().putLong(LAST_CACHED_TIME, value).apply()

    companion object {

        private const val PREFERENCES_NAME = "Main Screen Settings"

        private const val LAST_CACHED_TIME = "Last Cached Time"
    }
}