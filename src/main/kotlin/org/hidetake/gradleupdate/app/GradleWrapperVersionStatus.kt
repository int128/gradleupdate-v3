package org.hidetake.gradleupdate.app

import org.hidetake.gradleupdate.domain.GradleWrapperVersion

data class GradleWrapperVersionStatus(
    val targetGradleWrapperVersion: GradleWrapperVersion,
    val latestGradleWrapperVersion: GradleWrapperVersion
) {
    val upToDate = targetGradleWrapperVersion.isLaterThanOrEqualsTo(latestGradleWrapperVersion)
}
