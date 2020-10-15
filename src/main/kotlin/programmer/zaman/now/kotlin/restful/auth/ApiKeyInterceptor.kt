package programmer.zaman.now.kotlin.restful.auth

import org.springframework.stereotype.Component
import org.springframework.ui.ModelMap
import org.springframework.web.context.request.WebRequest
import org.springframework.web.context.request.WebRequestInterceptor
import programmer.zaman.now.kotlin.restful.error.UnauthorizeException
import programmer.zaman.now.kotlin.restful.repository.ApiKeyRepository
import java.lang.Exception

@Component
class ApiKeyInterceptor(val apiKeyRepository: ApiKeyRepository): WebRequestInterceptor {
    override fun preHandle(request: WebRequest) {
        val apiKey = request.getHeader("X-Api-Key")

        if (apiKey == null) {
            throw UnauthorizeException()
        }

        if (!apiKeyRepository.existsById(apiKey)) {
            throw UnauthorizeException()
        }
    }

    override fun postHandle(request: WebRequest, model: ModelMap?) {
        // un use
    }

    override fun afterCompletion(request: WebRequest, ex: Exception?) {
        // nothing
    }
}