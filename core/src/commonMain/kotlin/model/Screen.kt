package com.jetbrains.handson.mpp.mobile.model

import kotlinx.serialization.Serializable

@Serializable
data class Screen(val rows: List<Row>)