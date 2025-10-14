package fr.tokazio.konsistksp.exampleproject

import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RestController

typealias AnyTypeAlias = String

@RestController
@Validated
class ValidRestController {
    var anyProperty: String = "should not fail"

    fun anyFun(param1: String) {
        // should fail 'Functions of a controller should have validation annotation'
    }

    private fun anyPrivateFun(param1: String) {
        // is ok
    }

    protected fun anyProtectedFun(param1: String) {
        // is ok
    }

    internal fun anyInternalFun(param1: String) {
        // is ok
    }
}
