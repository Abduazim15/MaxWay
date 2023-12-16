package com.skipissue.maxway.data.settings

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SettingsImpl @Inject constructor(@ApplicationContext context: Context,
) : Settings {
    private val preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
    override var accessToken: String?
        get() = preferences.getString("token", null)
        set(value) {preferences.edit().putString("token", value).apply()}
    override var phoneNumber: String?
        get() = preferences.getString("phone", null)
        set(value) {preferences.edit().putString("phone", value).apply()}
    override var name: String?
        get() = preferences.getString("name", null)
        set(value) {preferences.edit().putString("name", value).apply()}
    override var id: String?
        get() =preferences.getString("id", null)
        set(value) {preferences.edit().putString("id", value).apply()}
    override var shipperId: String?
        get() =preferences.getString("shipper", null)
        set(value) {preferences.edit().putString("shipper", value).apply()}
}