package com.wcisang.kotlinmultiplatform.status

sealed class InformationStatus {
    object Holding : InformationStatus()
    class Success() : InformationStatus() {

        val results = mutableListOf<Any>()
        fun addResult(data: Any) {
            results.add(data)
        }
    }
    object Canceled : InformationStatus()
}