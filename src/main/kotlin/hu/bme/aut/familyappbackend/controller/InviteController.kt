package hu.bme.aut.familyappbackend.controller

import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Validated
@RequestMapping("\${api.base-path:/familyapp/FamilyApp/1.0.0}")
class InviteController {
}