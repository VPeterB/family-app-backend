package hu.bme.aut.familyappbackend.service

import hu.bme.aut.familyappbackend.dto.CreateShoppingItemDTO
import hu.bme.aut.familyappbackend.model.ShoppingItem
import hu.bme.aut.familyappbackend.model.ShoppingList
import hu.bme.aut.familyappbackend.repository.ShoppingItemRepository
import hu.bme.aut.familyappbackend.repository.ShoppingListRepository
import org.springframework.stereotype.Service

@Service
class ShoppingItemService (private val shoppingItemRepository: ShoppingItemRepository, private val shoppingListRepository: ShoppingListRepository){
    fun save (shoppingItem: CreateShoppingItemDTO, shoppingList: ShoppingList): ShoppingItem{
        val newSI = ShoppingItem(0, shoppingItem.name, shoppingItem.done, shoppingList)
        val shoppingItems: MutableList<ShoppingItem> = shoppingList.shoppingItems as MutableList<ShoppingItem>
        shoppingItems.add(newSI)
        shoppingList.shoppingItems = shoppingItems
        shoppingListRepository.save(shoppingList)
        return shoppingItemRepository.save(newSI)
    }

    fun delete (shoppingItem: ShoppingItem){
        val shoppingList = shoppingItem.shoppingList
        val lShoppingItems: MutableList<ShoppingItem> = shoppingList?.shoppingItems as MutableList<ShoppingItem>
        if (lShoppingItems.contains(shoppingItem)){
            lShoppingItems.remove(shoppingItem)
            shoppingList.shoppingItems = lShoppingItems
            shoppingListRepository.save(shoppingList)
        }
    }
}