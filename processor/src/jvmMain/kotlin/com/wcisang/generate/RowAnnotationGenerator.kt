package com.wcisang.generate

import com.wcisang.annotations.PagRow

internal class RowAnnotationGenerator
    : BaseAnnotationGenerator<PagRow>() {

    override val fileName: String
        get() = "RowMapper"
    override val packageName: String
        get() = "com.wcisang.kotlinmultiplatform.model"
    override val annotationType: Class<PagRow>
        get() = PagRow::class.java
    override val baseClassName: String
        get() = "Row"
}