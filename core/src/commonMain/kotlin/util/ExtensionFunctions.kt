package com.jetbrains.handson.mpp.mobile.util

import com.jetbrains.handson.mpp.mobile.model.actionrow.OpenUrlActionRow

fun String.buildUrl(nameQuery: String, query: String) : String{
    return "$this?$nameQuery=$query"
}