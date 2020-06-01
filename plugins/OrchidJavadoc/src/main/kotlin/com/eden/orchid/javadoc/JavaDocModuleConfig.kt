package com.eden.orchid.javadoc

import com.eden.orchid.sourcedoc.model.SourceDocModuleConfig
import javax.inject.Inject
import javax.inject.Named

class JavaDocModuleConfig
@Inject
constructor(
    @Named("javadocClasspath") private val javadocClasspath: String
) : SourceDocModuleConfig(JavadocGenerator.GENERATOR_KEY) {

    override fun additionalRunnerArgs(): List<String> = if (javadocClasspath.isNotBlank()) listOf(
        "-classpath",
        javadocClasspath,
        *args.toTypedArray()
    ) else args
}
