package ru.korotkov.party.model

import android.content.Context
import com.google.gson.Gson
import ru.korotkov.party.R
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader


class PartyInfoClient {

    fun getPartyInfo(context: Context): PartyInfo? {
        return getDataFromJson(context)
    }

    private fun getDataFromJson(context: Context): PartyInfo? {
        var streamReader: InputStreamReader? = null
        var fileInputStream: InputStream? = null
        try {
            fileInputStream = context.resources.openRawResource(R.raw.test_data)
            streamReader = InputStreamReader(fileInputStream)
            val gson = Gson()
            val data: PartyInfo = gson.fromJson(streamReader, PartyInfo::class.java)
            return data
        } catch (ex: IOException) {
            ex.printStackTrace()
        } finally {
            if (streamReader != null) {
                try {
                    streamReader.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
        return null
    }

    companion object {
        private var INSTANCE: PartyInfoClient? = null
        fun getInstance() = INSTANCE
            ?: PartyInfoClient().also {
                INSTANCE = it
            }
    }
}