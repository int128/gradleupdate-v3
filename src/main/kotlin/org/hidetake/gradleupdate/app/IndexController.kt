package org.hidetake.gradleupdate.app

import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.ModelAndView
import javax.validation.constraints.NotNull

@Controller
class IndexController {
    @GetMapping("/")
    fun index() = ModelAndView("index")

    @PostMapping("/landing")
    fun landing(@Validated repositoryForm: RepositoryForm, bindingResult: BindingResult) =
        when {
            bindingResult.hasErrors() -> "/"
            else -> repositoryForm.extractOwnerRepo()?.let { "$it/status" } ?: "/"
        }.let { uri ->
            "redirect:$uri"
        }

    data class RepositoryForm(@NotNull var url: String = "") {
        fun extractOwnerRepo(): String? = Regex("""[^/]+/[^/]+$""").find(url)?.value
    }
}
