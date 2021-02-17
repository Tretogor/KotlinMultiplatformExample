package com.jetbrains.handson.mpp.mobile.status

sealed class InformationStatus {
    object Holding : InformationStatus()
    data class Success(val data: Any) : InformationStatus()
    object Canceled : InformationStatus()
}