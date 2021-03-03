package com.wcisang.kotlinmultiplatform.middleware

import com.wcisang.kotlinmultiplatform.listeners.NavigationListener
import com.wcisang.kotlinmultiplatform.model.actionrow.NavigationAction
import com.wcisang.kotlinmultiplatform.model.actionrow.OpenNewScreenActionRow
import com.wcisang.kotlinmultiplatform.serializers.SerializerUtils
import com.wcisang.kotlinmultiplatform.state.AppState
import com.wcisang.kotlinmultiplatform.status.InformationStatus
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import org.reduxkotlin.middleware

fun navigationMiddleware(navigationListener: NavigationListener) = middleware<AppState> { store, next, action ->
    if (action is NavigationAction) {
        if (action is OpenNewScreenActionRow) {
            handleOpenNewScreenActionRow(action, navigationListener)
        }
    }
    val result = next(action)
    result
}

private fun handleOpenNewScreenActionRow(
    action: OpenNewScreenActionRow,
    navigationListener: NavigationListener
) {
    if (action.data.parameters != null && information is InformationStatus.Success) {
        var textObject = SerializerUtils.jsonSerialization.encodeToString(action.data.nextScreen)
        action.data.parameters.forEachIndexed { index, param ->
            textObject = textObject.replaceFirst("%s", (information as InformationStatus.Success).results[index] as String)
        }
        navigationListener.goTo(SerializerUtils.jsonSerialization.decodeFromString(textObject))
    }else {
        navigationListener.goTo(action.data.nextScreen)
    }

    information = InformationStatus.Holding

}
