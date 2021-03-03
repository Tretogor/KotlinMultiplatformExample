package com.wcisang.processor

import com.google.auto.service.AutoService
import com.wcisang.annotations.PagAction
import com.wcisang.annotations.PagRow
import com.wcisang.annotations.PagValidation
import com.wcisang.generate.ActionAnnotationGenerator
import com.wcisang.generate.BaseAnnotationGenerator
import com.wcisang.generate.RowAnnotationGenerator
import com.wcisang.generate.ValidationAnnotationGenerator
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement

const val KAPT_KOTLIN_GENERATED_OPTION_NAME = "kapt.kotlin.generated"

@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedOptions(KAPT_KOTLIN_GENERATED_OPTION_NAME)
@AutoService(Processor::class)
class PagProcessor: AbstractProcessor() {

    private val poolGenerator = listOf<BaseAnnotationGenerator<*>>(
        RowAnnotationGenerator(), ActionAnnotationGenerator(), ValidationAnnotationGenerator()
    )

    override fun process(p0: MutableSet<out TypeElement>?, roundEnv: RoundEnvironment): Boolean {

        val kaptKotlinGeneratedDir = processingEnv.options[KAPT_KOTLIN_GENERATED_OPTION_NAME]
            ?: throw Exception("Cannot access kapt generated")

        poolGenerator.forEach { generator ->
            roundEnv.getElementsAnnotatedWith(generator.annotationType)
                .takeIf { !it.isNullOrEmpty() }
                ?.let { generator.generateFile(it, kaptKotlinGeneratedDir) }
        }
        return false
    }

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        return mutableSetOf(PagRow::class.java.canonicalName,
            PagAction::class.java.canonicalName,
            PagValidation::class.java.canonicalName)
    }

}