package com.wcisang.kotlinmultiplatform.util

import com.wcisang.kotlinmultiplatform.model.actionrow.OpenUrlActionRow

fun String.buildUrl(nameQuery: String, query: String) : String{
    return "$this?$nameQuery=$query"
}