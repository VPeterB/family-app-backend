package hu.bme.aut.familyappbackend.dto

import lombok.Getter
import lombok.Setter

@Getter
@Setter
data class GetUserDTO(
    val id: Int = 0,
    val email: String,
    val password: String,
    val phonenumber: String? = null,
    val firstname: String? = null,
    val lastname: String? = null,
    val username: String? = null,
    val category: String? = null,
    var familyID: Int? = null,
    var inviteID: Int? = null,
    var shoppingListIDs: List<Int>? = null
){}
