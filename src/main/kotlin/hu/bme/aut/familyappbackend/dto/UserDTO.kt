package hu.bme.aut.familyappbackend.dto

import lombok.Getter
import lombok.Setter

@Getter
@Setter
data class UserDTO(
    val ID: Int = 0,
    val email: String,
    val password: String,
    val phonenumber: String? = null,
    val firstname: String? = null,
    val lastname: String? = null,
    val username: String? = null,
    val category: String? = null,
    val familyID: Int? = null,
    val inviteID: Int? = null,
    val shoppingListIDs: List<Int>? = null
){}
