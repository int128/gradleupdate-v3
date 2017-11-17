package org.hidetake.gradleupdate.domain

data class Branch(val name: String) {
    val ref = "refs/heads/$name"
}
