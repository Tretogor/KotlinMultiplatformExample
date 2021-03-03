package com.wcisang.kotlinmultiplatform.util

import com.wcisang.kotlinmultiplatform.model.actionrow.OpenUrlActionRow

fun String.buildUrl(nameQuery: List<OpenUrlActionRow.Query>, results: MutableList<String>) : String{
    val stringBuilder = StringBuilder()
    stringBuilder.append("$this?")
    nameQuery.forEachIndexed { index, query ->
        if (index>=1)
            stringBuilder.append("&")
        stringBuilder.append("${query.name}=${results[index]}")
    }
    return stringBuilder.toString()
}