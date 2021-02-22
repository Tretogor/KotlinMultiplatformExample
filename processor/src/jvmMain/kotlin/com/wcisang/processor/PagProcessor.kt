package com.wcisang.processor

import com.google.auto.service.AutoService
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.wcisang.annotations.PagAction
import com.wcisang.annotations.PagRow
import com.wcisang.annotations.PagValidation
import java.io.File
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.TypeElement
import kotlinx.serialization.modules.SerializersModuleBuilder

const val KAPT_KOTLIN_GENERATED_OPTION_NAME = "kapt.kotlin.generated"

@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedOptions(KAPT_KOTLIN_GENERATED_OPTION_NAME)
@AutoService(Processor::class)
class PagProcessor: AbstractProcessor() {

    override fun process(p0: MutableSet<out TypeElement>?, roundEnv: RoundEnvironment): Boolean {

        val kaptKotlinGeneratedDir = processingEnv.options[KAPT_KOTLIN_GENERATED_OPTION_NAME]
            ?: throw Exception("Cannot access kapt generated")

        roundEnv.getElementsAnnotatedWith(PagRow::class.java)
            .takeIf { !it.isNullOrEmpty() }
            ?.let { generateRowMapper(it, kaptKotlinGeneratedDir) }

        roundEnv.getElementsAnnotatedWith(PagAction::class.java)
            .takeIf { !it.isNullOrEmpty() }
            ?.let { generateActionMapper(it, kaptKotlinGeneratedDir) }

        roundEnv.getElementsAnnotatedWith(PagValidation::class.java)
            .takeIf { !it.isNullOrEmpty() }
            ?.let { generateValidationMapper(it, kaptKotlinGeneratedDir) }

        return false
    }

    private fun generateRowMapper(rows: Set<Element>, kaptKotlinGeneratedDir: String) {
        val fileName = "RowMapper"
        val pack = "com.wcisang.kotlinmultiplatform.model"
        val fileBuilder = FileSpec.builder(pack, fileName)
        val funcBuilder = FunSpec.builder("buildPolymorphicRow").receiver(SerializersModuleBuilder::class)
        rows.map {
            funcBuilder.addCode(
                """
                    polymorphic(Row::class, ${it.simpleName}::class, ${it.simpleName}.serializer())
                """.trimMargin()
            )
        }
        fileBuilder.addFunction(funcBuilder.build())
        fileBuilder.build().writeTo(File(kaptKotlinGeneratedDir))
    }

    private fun generateActionMapper(rows: Set<Element>, kaptKotlinGeneratedDir: String) {
        val fileName = "ActionMapper"
        val pack = "com.wcisang.kotlinmultiplatform.model.actionrow"
        val fileBuilder = FileSpec.builder(pack, fileName)
        val funcBuilder = FunSpec.builder("buildPolymorphicAction").receiver(SerializersModuleBuilder::class)
        rows.map {
            funcBuilder.addCode(
                """
                    polymorphic(ActionRow::class, ${it.simpleName}::class, ${it.simpleName}.serializer())
                """.trimMargin()
            )
        }
        fileBuilder.addFunction(funcBuilder.build())
        fileBuilder.build().writeTo(File(kaptKotlinGeneratedDir))
    }

    private fun generateValidationMapper(rows: Set<Element>, kaptKotlinGeneratedDir: String) {
        val fileName = "ValidationMapper"
        val pack = "com.wcisang.kotlinmultiplatform.model.validation"
        val fileBuilder = FileSpec.builder(pack, fileName)
        val funcBuilder = FunSpec.builder("buildPolymorphicValidation").receiver(SerializersModuleBuilder::class)
        rows.map {
            funcBuilder.addCode(
                """
                    polymorphic(InputValidation::class, ${it.simpleName}::class, ${it.simpleName}.serializer())
                """.trimMargin()
            )
        }
        fileBuilder.addFunction(funcBuilder.build())
        fileBuilder.build().writeTo(File(kaptKotlinGeneratedDir))
    }

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        return mutableSetOf(PagRow::class.java.canonicalName,
            PagAction::class.java.canonicalName,
            PagValidation::class.java.canonicalName)
    }

}