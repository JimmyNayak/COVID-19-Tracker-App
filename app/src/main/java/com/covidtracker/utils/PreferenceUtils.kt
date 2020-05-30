package com.covidtracker.utils

import android.content.Context
import com.google.gson.Gson

/**
 * utility for shared preferences
 *
 *
 * contains setters and getters for different types of data
 */
class PreferenceUtils //no direct instances allowed. use di instead.
internal constructor(private val mContext: Context, private val mGson: Gson) {

    private val mPrefName = "covid_shared_prefs"

    private val mNumValue = -1

    private val mStringValue = ""

    fun saveData(key: String?, value: Any?) {
        val prefs = mContext.getSharedPreferences(
            mPrefName,
            Context.MODE_PRIVATE
        )
        val editor = prefs.edit()


        when (value) {
            is String -> {
                editor.putString(key, value)
            }
            is Int -> {
                editor.putInt(key, value)
            }
            is Boolean -> {
                editor.putBoolean(key, value)
            }
            is Long -> {
                editor.putLong(key, value)
            }
            is Float -> {
                editor.putFloat(key, value)
            }
            is Any -> {
                editor.putString(key, mGson.toJson(value))
            }
        }


        editor.apply()
    }

    fun getString(key: String?): String? {
        val prefs = mContext.getSharedPreferences(
            mPrefName,
            Context.MODE_PRIVATE
        )
        return prefs.getString(key, mStringValue)
    }


    fun getBoolean(key: String?): Boolean {
        val prefs = mContext.getSharedPreferences(
            mPrefName,
            Context.MODE_PRIVATE
        )
        return prefs.getBoolean(key, false)
    }

    fun getLong(key: String?): Long {
        val prefs = mContext.getSharedPreferences(
            mPrefName,
            Context.MODE_PRIVATE
        )
        return prefs.getLong(key, mNumValue.toLong())
    }

    fun getInteger(key: String?): Int {
        val prefs = mContext.getSharedPreferences(
            mPrefName,
            Context.MODE_PRIVATE
        )
        return prefs.getInt(key, mNumValue)
    }

    fun getFloat(key: String?): Float {
        val prefs = mContext.getSharedPreferences(
            mPrefName,
            Context.MODE_PRIVATE
        )
        return prefs.getFloat(key, mNumValue.toFloat())
    }

    fun <T> getObject(key: String?, pojoClass: Class<T>?): T? {
        val prefs = mContext.getSharedPreferences(
            mPrefName,
            Context.MODE_PRIVATE
        )
        val jsonString = prefs.getString(key, null) ?: return null
        return mGson.fromJson(jsonString, pojoClass)
    }


    fun removeKey(key: String?) {
        val prefs = mContext.getSharedPreferences(
            mPrefName,
            Context.MODE_PRIVATE
        )
        val editor = prefs.edit()
        editor.remove(key)
        editor.apply()
    }


    fun clearAll() {
        val prefs = mContext.getSharedPreferences(
            mPrefName,
            Context.MODE_PRIVATE
        )
        val editor = prefs.edit()
        editor.clear()
        editor.apply()
    }


    fun isKeyAvailable(key: String?): Boolean {
        val sharedPrefs = mContext.getSharedPreferences(
            mPrefName,
            Context.MODE_PRIVATE
        )
        return sharedPrefs.contains(key)
    }

}