package com.example.languagetest

import android.content.Context
import android.content.ContextWrapper
import android.content.res.Resources
import android.os.Build
import android.os.LocaleList
import java.util.*

object LocaleHelper {

    private const val LANGUAGE_CODE_SIZE = 2

    fun getLocale(lcid: String?): Locale {
        if (lcid == null) {
            return Locale("en", "US")
        }
        if (lcid == "sw") return Locale("sw")
        val languageMeta = lcid.split("-")
        return if (languageMeta.size != LANGUAGE_CODE_SIZE) {
            Locale("en", "US")
        } else {
            Locale(languageMeta.first(), languageMeta.last().uppercase())
        }
    }

    fun updateLocale(ctx: Context, languageCode: String?): ContextWrapper {
        var context: Context = ctx

        val locale = getLocale(languageCode)
        val resources: Resources = context.resources
        val configuration = resources.configuration

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val localeList = LocaleList(locale)
            LocaleList.setDefault(localeList)
            configuration.setLocale(locale)
            configuration.setLocales(localeList)
        } else {
            configuration.locale = locale
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            context = context.createConfigurationContext(configuration)
        } else {
            resources.updateConfiguration(configuration, resources.displayMetrics)
        }
        return ContextWrapper(context)
    }

}
