package programmer.zaman.now.kotlin.restful.controller

import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import programmer.zaman.now.kotlin.restful.error.NotFoundException
import programmer.zaman.now.kotlin.restful.error.UnauthorizeException
import programmer.zaman.now.kotlin.restful.model.WebResponse
import javax.validation.ConstraintViolationException

@RestControllerAdvice
class ErrorController {
    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun validationHandler(constraintViolationException: ConstraintViolationException): WebResponse<String> {
        return WebResponse(
                code = 400,
                status = "BAD REQUEST",
                data = constraintViolationException.message!!
        )
    }

    @ExceptionHandler(value = [NotFoundException::class])
    fun notFound(notFoundException: NotFoundException): WebResponse<String> {
        return WebResponse(
                code = 404,
                status = "Not Found",
                data = "Not Found"
        )
    }

    @ExceptionHandler(value = [UnauthorizeException::class])
    fun notFound(notFoundException: UnauthorizeException): WebResponse<String> {
        return WebResponse(
                code = 401,
                status = "Unauthorized",
                data = "Please put api key"
        )
    }
}