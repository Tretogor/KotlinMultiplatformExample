package com.wcisang.generate

import com.wcisang.annotations.PagValidation

internal class ValidationAnnotationGenerator
    : BaseAnnotationGenerator<PagValidation>() {

    override val fileName: String
        get() = "ValidationMapper"
    override val packageName: String
        get() = "com.wcisang.kotlinmultiplatform.model.validation"
    override val annotationType: Class<PagValidation>
        get() = PagValidation::class.java
    override val baseClassName: String
        get() = "InputValidation"
}