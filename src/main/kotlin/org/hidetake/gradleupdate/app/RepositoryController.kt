package org.hidetake.gradleupdate.app

import org.hidetake.gradleupdate.domain.BranchName
import org.hidetake.gradleupdate.domain.RepositoryPath
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import javax.servlet.http.HttpServletResponse

@Controller
@RequestMapping("/{owner}/{repo}")
class RepositoryController(val service: GradleUpdateService) {
    @GetMapping("status.svg")
    fun badge(
        @PathVariable owner: String,
        @PathVariable repo: String,
        @RequestParam("branch", required = false) branch: String?,
        response: HttpServletResponse
    ): ResponseEntity<String> {
        val status = when (branch) {
            null -> service.getGradleWrapperVersionStatus(RepositoryPath(owner, repo))
            else -> service.getGradleWrapperVersionStatus(RepositoryPath(owner, repo), BranchName(branch))
        }

        // https://docs.spring.io/spring-security/site/docs/current/reference/html/headers.html#headers-cache-control
        response.setHeader(HttpHeaders.CACHE_CONTROL, "public, max-age=30")

        return when (status) {
            null -> TODO()
            else -> BadgeSvg.render(status)
        }
    }
}
