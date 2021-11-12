package com.rinats.rin.interceptor

import com.rinats.rin.annotation.NonAuth
import com.rinats.rin.annotation.PartTimeJob
import com.rinats.rin.annotation.StoreManager
import com.rinats.rin.repository.AuthInfoRepository
import com.rinats.rin.repository.EmployeeRepository
import org.springframework.core.annotation.AnnotationUtils
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1

class AuthInterceptor(
    private val authInfoRepository: AuthInfoRepository,
    private val employeeRepository: EmployeeRepository
) : HandlerInterceptor {

    override fun preHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any
    ): Boolean {

        val hm = try {
            HandlerMethod::class.java.cast(handler)
        } catch (e: ClassCastException) {
            return false
        }
        val method = hm.method

        val isNonAuth = AnnotationUtils.findAnnotation(method, NonAuth::class.java) != null
        if (isNonAuth) {
            return true
        }

        val accessToken = request.getParameter("accessToken")
        if (!checkAccessToken(accessToken)) {
            return false
        }

        val employeeId = authInfoRepository.findByAccessToken(accessToken).get().employeeId
        val employee = employeeRepository.findById(employeeId).get()
        val isStoreManagerResource = AnnotationUtils.findAnnotation(method, StoreManager::class.java) != null
        val isPartTimeJobResource = AnnotationUtils.findAnnotation(method, PartTimeJob::class.java) != null

        if (isStoreManagerResource && employee.roleId != "1") {
            return false
        }
        if (isPartTimeJobResource && employee.roleId != "2") {
            return false
        }

        request.setAttribute("employee", employee)
        return true
    }

    private fun checkAccessToken(accessToken: String?): Boolean {
        accessToken ?: return false
        return authInfoRepository.existsByAccessToken(accessToken)
    }
}