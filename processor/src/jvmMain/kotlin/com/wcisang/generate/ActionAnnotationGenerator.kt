package com.wcisang.generate

import com.wcisang.annotations.PagAction

internal class ActionAnnotationGenerator
    : BaseAnnotationGenerator<PagAction>() {

    override val fileName: String
        get() = "ActionMapper"
    override val packageName: String
        get() = "com.wcisang.kotlinmultiplatform.model.actionrow"
    override val annotationType: Class<PagAction>
        get() = PagAction::class.java
    override val baseClassName: String
        get() = "ActionRow"
}