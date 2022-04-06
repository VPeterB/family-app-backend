package hu.bme.aut.familyappbackend.repository

import hu.bme.aut.familyappbackend.model.ShoppingList
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ShoppingListRepository : CrudRepository<ShoppingList, Int> {
    fun findShoppingListByID(ID: Int): ShoppingList?
}