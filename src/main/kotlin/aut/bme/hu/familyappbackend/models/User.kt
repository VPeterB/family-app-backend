/**
* Family App API
* This is a familyapp API
*
* OpenAPI spec version: 1.0.0
* Contact: you@your-company.com
*
* NOTE: This class is auto generated by the swagger code generator program.
* https://github.com/swagger-api/swagger-codegen.git
* Do not edit the class manually.
*/
package io.swagger.server.models

import io.swagger.server.models.ByteArray
import io.swagger.server.models.Family
import io.swagger.server.models.Invite
import io.swagger.server.models.ShoppingList

/**
 * 
 * @param ID 
 * @param email 
 * @param password 
 * @param phonenumber 
 * @param firstname 
 * @param lastname 
 * @param username 
 * @param category 
 * @param profilepicture 
 * @param familyID 
 * @param Family 
 * @param invites 
 * @param shoppinglists 
 */
data class User (
    val ID: java.util.UUID,
    val email: kotlin.String,
    val password: kotlin.String,
    val phonenumber: kotlin.String? = null,
    val firstname: kotlin.String? = null,
    val lastname: kotlin.String? = null,
    val username: kotlin.String? = null,
    val category: kotlin.String? = null,
    val profilepicture: ByteArray? = null,
    val familyID: java.util.UUID? = null,
    val Family: Family? = null,
    val invites: kotlin.Array<Invite>? = null,
    val shoppinglists: kotlin.Array<ShoppingList>? = null
) {

}

