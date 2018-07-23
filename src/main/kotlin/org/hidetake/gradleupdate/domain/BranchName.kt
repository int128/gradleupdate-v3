package org.hidetake.gradleupdate.domain

data class BranchName(val name: String) {
    val ref = "refs/heads/$name"
}
