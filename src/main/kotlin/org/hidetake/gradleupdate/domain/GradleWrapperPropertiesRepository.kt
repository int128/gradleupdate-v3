package org.hidetake.gradleupdate.domain

interface GradleWrapperPropertiesRepository {
    fun find(repositoryPath: RepositoryPath): GradleWrapperProperties?
    fun find(repositoryPath: RepositoryPath, branchName: BranchName): GradleWrapperProperties?
}
