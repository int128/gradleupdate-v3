package org.hidetake.gradleupdate.infrastructure

import org.eclipse.egit.github.core.service.ContentsService
import org.hidetake.gradleupdate.domain.*
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class DefaultGradleWrapperPropertiesRepository(client: SystemGitHubClient) : GradleWrapperPropertiesRepository {
    private val contentsService = ContentsService(client)

    override fun find(repositoryPath: RepositoryPath): GradleWrapperProperties? =
        findContent(repositoryPath, "gradle/wrapper/gradle-wrapper.properties")
            ?.let { String(Base64.getMimeDecoder().decode(it.content)) }
            ?.let { GradleWrapperProperties(it) }

    override fun find(repositoryPath: RepositoryPath, branchName: BranchName): GradleWrapperProperties? =
        findContent(repositoryPath, branchName, "gradle/wrapper/gradle-wrapper.properties")
            ?.let { String(Base64.getMimeDecoder().decode(it.content)) }
            ?.let { GradleWrapperProperties(it) }

    private fun findContent(repositoryPath: RepositoryPath, path: String) =
        nullIfNotFound {
            contentsService.getContents({ repositoryPath.fullName }, path).firstOrNull()
        }

    private fun findContent(repositoryPath: RepositoryPath, branchName: BranchName, path: String) =
        nullIfNotFound {
            contentsService.getContents({ repositoryPath.fullName }, path, branchName.ref).firstOrNull()
        }
}
