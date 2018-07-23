package org.hidetake.gradleupdate.app

import org.eclipse.egit.github.core.Repository
import org.hidetake.gradleupdate.domain.GradleWrapperPropertiesRepository
import org.hidetake.gradleupdate.domain.GradleWrapperVersion
import org.hidetake.gradleupdate.domain.RepositoryPath
import org.hidetake.gradleupdate.domain.RepositoryRepository
import org.springframework.stereotype.Service

@Service
class GradleUpdateService(
    private val repositoryRepository: RepositoryRepository,
    private val gradleWrapperPropertiesRepository: GradleWrapperPropertiesRepository
) {
    private val LATEST_GRADLE_WRAPPER = RepositoryPath("int128", "latest-gradle-wrapper")

    fun getRepositoryMetadata(repositoryPath: RepositoryPath): Repository =
        repositoryRepository.get(repositoryPath)

    fun getGradleWrapperVersionStatus(repositoryPath: RepositoryPath): GradleWrapperVersionStatus {
        val target = gradleWrapperPropertiesRepository.find(repositoryPath)?.version
        when (target) {
            null ->
        }
        val latest = getLatestGradleWrapperVersion()
    }

    fun getLatestGradleWrapperVersion(): GradleWrapperVersion =
        gradleWrapperPropertiesRepository.find(LATEST_GRADLE_WRAPPER)?.version
            ?: throw IllegalStateException("Not found latest Gradle Wrapper in $LATEST_GRADLE_WRAPPER")
}
