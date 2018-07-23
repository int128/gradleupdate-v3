package org.hidetake.gradleupdate.domain

import org.eclipse.egit.github.core.Repository

interface RepositoryRepository {
    fun get(repositoryPath: RepositoryPath): Repository
}
