package org.hidetake.gradleupdate.domain

class GradleWrapperProperties(val content: String) {
    val version: GradleWrapperVersion? by lazy {
        Regex("""distributionUrl=.+?/gradle-(.+?)-.+?\.zip""")
            .find(content)
            ?.groupValues
            ?.let { m -> GradleWrapperVersion(m[1]) }
    }
}
