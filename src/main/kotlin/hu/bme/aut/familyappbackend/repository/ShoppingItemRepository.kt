package hu.bme.aut.familyappbackend.repository

import hu.bme.aut.familyappbackend.model.ShoppingItem
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ShoppingItemRepository : CrudRepository<ShoppingItem, Int> {
    fun findShoppingItemById(id: Int): ShoppingItem?
}