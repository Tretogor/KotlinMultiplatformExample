package com.wcisang.kotlinmultiplatform.model

import kotlinx.serialization.Serializable

@Serializable
data class Screen(val rows: List<Row>)