package org.hidetake.gradleupdate.domain

data class RepositoryPath(val owner: String, val name: String)  {
    val fullName = "$owner/$name"
}
