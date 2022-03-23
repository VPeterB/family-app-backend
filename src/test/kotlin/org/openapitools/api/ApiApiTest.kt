package hu.bme.aut.familyappbackend.api

import hu.bme.aut.familyappbackend.controller.*
import hu.bme.aut.familyappbackend.model.Family
import hu.bme.aut.familyappbackend.model.InlineObject
import hu.bme.aut.familyappbackend.model.InlineObject1
import hu.bme.aut.familyappbackend.model.InlineObject2
import hu.bme.aut.familyappbackend.model.InlineObject3
import hu.bme.aut.familyappbackend.model.InlineResponse200
import hu.bme.aut.familyappbackend.model.ShoppingItem
import hu.bme.aut.familyappbackend.model.ShoppingList
import hu.bme.aut.familyappbackend.model.User
import org.junit.jupiter.api.Test
import org.springframework.http.ResponseEntity

class ApiApiTest {

    private val siapi: ShoppingItemController = ShoppingItemController()
    private val fapi: FamilyController = FamilyController()
    private val iapi: InviteController = InviteController()
    private val slapi: ShoppingListController = ShoppingListController()
    private val uapi: UserController = UserController()

    /**
     * To test ApiApiController.addShoppingItem
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun addShoppingItemTest() {
        val shoppinglistID: kotlin.String = TODO()
        val shoppingitem: ShoppingItem = TODO()
        val response: ResponseEntity<Unit> = siapi.addShoppingItem(shoppinglistID, shoppingitem)

        // TODO: test validations
    }

    /**
     * To test ApiApiController.addUserToFamily
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun addUserToFamilyTest() {
        val familyID: kotlin.String = TODO()
        val userID: kotlin.String = TODO()
        val response: ResponseEntity<Unit> = fapi.addUserToFamily(familyID, userID)

        // TODO: test validations
    }

    /**
     * To test ApiApiController.addUserToShoppingList
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun addUserToShoppingListTest() {
        val shoppinglistID: kotlin.String = TODO()
        val userID: kotlin.String = TODO()
        val response: ResponseEntity<Unit> = slapi.addUserToShoppingList(shoppinglistID, userID)

        // TODO: test validations
    }

    /**
     * To test ApiApiController.createFamily
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun createFamilyTest() {
        val picture: kotlin.ByteArray? = TODO()
        val response: ResponseEntity<Unit> = fapi.createFamily(picture)

        // TODO: test validations
    }

    /**
     * To test ApiApiController.createShoppingList
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun createShoppingListTest() {
        val shoppinglistcreate: InlineObject3 = TODO()
        val response: ResponseEntity<Unit> = slapi.createShoppingList(shoppinglistcreate)

        // TODO: test validations
    }

    /**
     * To test ApiApiController.deleteFamily
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun deleteFamilyTest() {
        val familyID: kotlin.String = TODO()
        val response: ResponseEntity<Unit> = fapi.deleteFamily(familyID)

        // TODO: test validations
    }

    /**
     * To test ApiApiController.deleteShoppingItem
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun deleteShoppingItemTest() {
        val shoppingitemID: kotlin.String = TODO()
        val shoppinglistID: kotlin.String = TODO()
        val response: ResponseEntity<Unit> = siapi.deleteShoppingItem(shoppingitemID, shoppinglistID)

        // TODO: test validations
    }

    /**
     * To test ApiApiController.deleteShoppingList
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun deleteShoppingListTest() {
        val shoppinglistID: kotlin.String = TODO()
        val response: ResponseEntity<Unit> = slapi.deleteShoppingList(shoppinglistID)

        // TODO: test validations
    }

    /**
     * To test ApiApiController.deleteUser
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun deleteUserTest() {
        val userID: kotlin.String = TODO()
        val response: ResponseEntity<Unit> = uapi.deleteUser(userID)

        // TODO: test validations
    }

    /**
     * To test ApiApiController.doneShoppingItem
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun doneShoppingItemTest() {
        val shoppinglistID: kotlin.String = TODO()
        val shoppingitemID: kotlin.String = TODO()
        val response: ResponseEntity<Unit> = siapi.doneShoppingItem(shoppinglistID, shoppingitemID)

        // TODO: test validations
    }

    /**
     * To test ApiApiController.editFamily
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun editFamilyTest() {
        val familyID: kotlin.String = TODO()
        val family: Family = TODO()
        val response: ResponseEntity<Unit> = fapi.editFamily(familyID, family)

        // TODO: test validations
    }

    /**
     * To test ApiApiController.editShoppingItem
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun editShoppingItemTest() {
        val shoppingitemID: kotlin.String = TODO()
        val shoppinglistID: kotlin.String = TODO()
        val shoppingitem: ShoppingItem = TODO()
        val response: ResponseEntity<Unit> = siapi.editShoppingItem(shoppingitemID, shoppinglistID, shoppingitem)

        // TODO: test validations
    }

    /**
     * To test ApiApiController.editShoppingList
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun editShoppingListTest() {
        val shoppinglistID: kotlin.String = TODO()
        val shoppinglist: ShoppingList = TODO()
        val response: ResponseEntity<Unit> = slapi.editShoppingList(shoppinglistID, shoppinglist)

        // TODO: test validations
    }

    /**
     * To test ApiApiController.editUser
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun editUserTest() {
        val userID: kotlin.String = TODO()
        val user: User? = TODO()
        val response: ResponseEntity<Unit> = uapi.editUser(userID, user)

        // TODO: test validations
    }

    /**
     * To test ApiApiController.getAllShoppingItem
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun getAllShoppingItemTest() {
        val shoppinglistID: kotlin.String = TODO()
        val response: ResponseEntity<List<InlineResponse200>> = siapi.getAllShoppingItem(shoppinglistID)

        // TODO: test validations
    }

    /**
     * To test ApiApiController.getFamily
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun getFamilyTest() {
        val familyID: kotlin.String = TODO()
        val response: ResponseEntity<Unit> = fapi.getFamily(familyID)

        // TODO: test validations
    }

    /**
     * To test ApiApiController.getShoppingItem
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun getShoppingItemTest() {
        val shoppingitemID: kotlin.String = TODO()
        val shoppinglistID: kotlin.String = TODO()
        val response: ResponseEntity<Unit> = siapi.getShoppingItem(shoppingitemID, shoppinglistID)

        // TODO: test validations
    }

    /**
     * To test ApiApiController.getShoppingList
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun getShoppingListTest() {
        val shoppinglistID: kotlin.String = TODO()
        val response: ResponseEntity<Unit> = slapi.getShoppingList(shoppinglistID)

        // TODO: test validations
    }

    /**
     * To test ApiApiController.getShoppingListbyFamily
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun getShoppingListbyFamilyTest() {
        val familyID: kotlin.String = TODO()
        val response: ResponseEntity<List<InlineResponse200>> = slapi.getShoppingListbyFamily(familyID)

        // TODO: test validations
    }

    /**
     * To test ApiApiController.getUser
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun getUserTest() {
        val userID: kotlin.String = TODO()
        val response: ResponseEntity<Unit> = uapi.getUser(userID)

        // TODO: test validations
    }

    /**
     * To test ApiApiController.inviteUser
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun inviteUserTest() {
        val invite: InlineObject2? = TODO()
        val response: ResponseEntity<Unit> = uapi.inviteUser(invite)

        // TODO: test validations
    }

    /**
     * To test ApiApiController.login
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun loginTest() {
        val user: InlineObject1? = TODO()
        val response: ResponseEntity<Unit> = uapi.login(user)

        // TODO: test validations
    }

    /**
     * To test ApiApiController.reg
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun regTest() {
        val user: InlineObject? = TODO()
        val response: ResponseEntity<Unit> = uapi.reg(user)

        // TODO: test validations
    }

    /**
     * To test ApiApiController.removeUserFromFamily
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun removeUserFromFamilyTest() {
        val familyID: kotlin.String = TODO()
        val user: kotlin.String = TODO()
        val response: ResponseEntity<Unit> = fapi.removeUserFromFamily(familyID, user)

        // TODO: test validations
    }

    /**
     * To test ApiApiController.removeUserFromShoppingList
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun removeUserFromShoppingListTest() {
        val shoppinglistID: kotlin.String = TODO()
        val userID: kotlin.String = TODO()
        val response: ResponseEntity<Unit> = slapi.removeUserFromShoppingList(shoppinglistID, userID)

        // TODO: test validations
    }

    /**
     * To test ApiApiController.undoneShoppingItem
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun undoneShoppingItemTest() {
        val shoppinglistID: kotlin.String = TODO()
        val shoppingitemID: kotlin.String = TODO()
        val response: ResponseEntity<Unit> = siapi.undoneShoppingItem(shoppinglistID, shoppingitemID)

        // TODO: test validations
    }

}
